package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val isChecked: Boolean,
)
