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

    @Query("UPDATE todo SET alarm = :alarm WHERE id = :id")
    suspend fun updateAlarm(id: Int, alarm: Boolean)

    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Int): Flow<Todo>
}