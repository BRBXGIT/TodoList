package com.example.todolist.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDao
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {
    fun updateExistingTodo(
        id: Int,
        title: String,
        startTime: String,
        endTime: String,
        date: String,
        isChecked: Boolean,
        dao: TodoDao
    ) {
        viewModelScope.launch {
            dao.updateTodo(
                Todo(
                    id = id,
                    title = title,
                    startTime = startTime,
                    endTime = endTime,
                    date = date,
                    isChecked = !isChecked
                )
            )
        }
    }
}