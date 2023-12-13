package com.example.todolist.data.TodoData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.TodoData.Todo
import com.example.todolist.data.TodoData.TodoDao

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}