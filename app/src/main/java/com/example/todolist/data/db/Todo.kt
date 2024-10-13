package com.example.todolist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val completed: Boolean
)
