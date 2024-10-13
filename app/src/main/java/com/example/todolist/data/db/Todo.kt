package com.example.todolist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val time: String,
    val date: String,
    val description: String,
    val completed: Boolean = false
)
