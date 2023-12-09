package com.example.todolist

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.todolist.data.TodoDb
import com.example.todolist.ui.add_todo_screen.AddTodo
import com.example.todolist.ui.theme.TodoListTheme

class AddTodoActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDb::class.java,
            "Todo db"
        ).build()
    }

    private val todoDao by lazy {
        db.todoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {

                //Create system bars translucent
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )
                AddTodo(dao = todoDao)
            }
        }
    }
}