package com.example.todolist.presentation.info_screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.todolist.presentation.info_screen.screen.InfoScreen
import kotlinx.serialization.Serializable

@Serializable
object InfoScreenRoute

fun NavGraphBuilder.infoScreen(
    navController: NavHostController
) = composable<InfoScreenRoute> {
    InfoScreen(navController)
}