package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Upsert
    fun upsertTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllTodos(): Flow<List<Todo>>
}