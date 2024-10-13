package com.example.todolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Todo::class]
)
abstract class TodoDb: RoomDatabase() {

    abstract fun todoDao(): TodoDao
}