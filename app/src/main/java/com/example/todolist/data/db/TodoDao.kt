package com.example.todolist.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Upsert
    suspend fun upsertTodo(todo: Todo)

    @Query("UPDATE todo SET completed = :completed WHERE id = :id")
    suspend fun updateTodoCompleted(id: Int, completed: Boolean)

    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>
}