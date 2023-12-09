package com.example.todolist.ui.add_todo_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDao
import kotlinx.coroutines.launch

class AddTodoViewModel: ViewModel() {
    fun upsertNewTodo(dao: TodoDao) {
        viewModelScope.launch {
            dao.upsertTodo(
                Todo(
                    0,
                    "",
                    "",
                    "",
                    "",
                    false
                )
            )
        }
    }
}