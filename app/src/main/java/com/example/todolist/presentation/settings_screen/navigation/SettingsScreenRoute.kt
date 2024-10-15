package com.example.todolist.presentation.settings_screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.todolist.presentation.settings_screen.screen.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsScreenRoute

fun NavGraphBuilder.settingsScreen(
    navController: NavHostController
) = composable<SettingsScreenRoute> {
    SettingsScreen(navController)
}