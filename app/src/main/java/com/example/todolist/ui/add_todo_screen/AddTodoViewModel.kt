package com.example.todolist.ui.add_todo_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoData.Todo
import com.example.todolist.data.TodoData.TodoDao
import kotlinx.coroutines.launch

class AddTodoViewModel: ViewModel() {
    fun upsertNewTodo(
        dao: TodoDao,
        title: String,
        startTime: String,
        endTime: String,
        date: String
    ) {
        viewModelScope.launch {
            dao.upsertTodo(
                Todo(
                    0,
                    title,
                    startTime,
                    endTime,
                    date,
                    false
                )
            )
        }
    }
}