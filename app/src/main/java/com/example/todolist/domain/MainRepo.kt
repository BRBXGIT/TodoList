package com.example.todolist.domain

import com.example.todolist.data.db.Todo
import kotlinx.coroutines.flow.Flow

interface MainRepo {

    suspend fun upsertTodo(todo: Todo)

    suspend fun updateTodoCompleted(id: Int, completed: Boolean)

    fun getAllTodo(): Flow<List<Todo>>
}