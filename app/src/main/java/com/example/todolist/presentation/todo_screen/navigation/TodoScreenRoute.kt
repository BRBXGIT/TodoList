package com.example.todolist.presentation.todo_screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBarVM
import com.example.todolist.presentation.todo_screen.screen.TodoScreen
import com.example.todolist.presentation.todo_screen.screen.TodoScreenVM
import kotlinx.serialization.Serializable

@Serializable
object TodoScreenRoute

fun NavGraphBuilder.todoScreen(
    navController: NavHostController
) = composable<TodoScreenRoute> {
    val todoScreenVM = hiltViewModel<TodoScreenVM>()
    val todoScreenTopBarVM = viewModel<TodoScreenTopBarVM>()

    TodoScreen(
        todoScreenVM = todoScreenVM,
        todoScreenTopBarVM = todoScreenTopBarVM,
        navController = navController
    )
}