package com.example.todolist

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.todolist.data.TodoData.TodoDb
import com.example.todolist.ui.main_screen.MainScreen
import com.example.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {

    private val todoDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDb::class.java,
            "Todo db"
        ).build()
    }

    private val todoDao by lazy {
        todoDb.todoDao()
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
                MainScreen(todoDao = todoDao)
            }
        }
    }
}

