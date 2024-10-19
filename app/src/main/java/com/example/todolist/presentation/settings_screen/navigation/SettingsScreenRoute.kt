package com.example.todolist.presentation.settings_screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.todolist.common.app_settings.AppSettingsVM
import com.example.todolist.presentation.settings_screen.screen.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsScreenRoute

fun NavGraphBuilder.settingsScreen(
    navController: NavHostController,
    appSettingsVM: AppSettingsVM
) = composable<SettingsScreenRoute> {
    SettingsScreen(
        navController = navController,
        appSettingsVM = appSettingsVM
    )
}