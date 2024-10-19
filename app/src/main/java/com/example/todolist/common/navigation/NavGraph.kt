package com.example.todolist.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.todolist.common.app_settings.AppSettingsVM
import com.example.todolist.presentation.settings_screen.navigation.settingsScreen
import com.example.todolist.presentation.todo_screen.navigation.TodoScreenRoute
import com.example.todolist.presentation.todo_screen.navigation.todoScreen

@Composable
fun NavGraph(
    appSettingsVM: AppSettingsVM
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TodoScreenRoute
    ) {
        todoScreen(navController)

        settingsScreen(
            navController = navController,
            appSettingsVM = appSettingsVM
        )
    }
}