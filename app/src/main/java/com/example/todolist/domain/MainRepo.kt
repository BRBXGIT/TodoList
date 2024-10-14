package com.example.todolist.domain

import com.example.todolist.data.db.Todo
import kotlinx.coroutines.flow.Flow

interface MainRepo {

    suspend fun upsertTodo(todo: Todo)

    suspend fun updateTodoCompleted(id: Int, completed: Boolean)

    suspend fun updateAlarm(id: Int, alarm: Boolean)

    fun getAllTodo(): Flow<List<Todo>>

    fun getTodoById(id: Int): Flow<Todo>
}