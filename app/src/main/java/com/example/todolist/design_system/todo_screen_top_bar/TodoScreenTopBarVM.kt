package com.example.todolist.design_system.todo_screen_top_bar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodoScreenTopBarVM: ViewModel() {
    private var date = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")

    private val _selectedDate = MutableStateFlow(date.format(formatter))
    val selectedDate = _selectedDate.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    fun dateMinusDay() {
        date = date.minusDays(1)
        _selectedDate.value = date.format(formatter)
    }

    fun datePlusDay() {
        date = date.plusDays(1)
        _selectedDate.value = date.format(formatter)
    }

    fun setDate(newDate: LocalDateTime) {
        date = newDate
        _selectedDate.value = date.format(formatter)
    }
}