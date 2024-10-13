package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBarVM
import com.example.todolist.presentation.screen.TodoScreen
import com.example.todolist.presentation.screen.TodoScreenVM
import com.example.todolist.ui.theme.TodoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                val todoScreenVM = hiltViewModel<TodoScreenVM>()
                val todoScreenTopBarVM = viewModel<TodoScreenTopBarVM>()

                TodoScreen(
                    todoScreenVM = todoScreenVM,
                    todoScreenTopBarVM = todoScreenTopBarVM
                )
            }
        }
    }
}