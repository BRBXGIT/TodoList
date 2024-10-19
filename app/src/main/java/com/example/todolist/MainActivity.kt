package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.common.app_settings.ThemeVM
import com.example.todolist.common.navigation.NavGraph
import com.example.todolist.ui.theme.TodoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeVM = hiltViewModel<ThemeVM>()
            val theme by themeVM.theme.collectAsState(initial = "default")
            val colorSystem by themeVM.colorSystem.collectAsState(initial = "default")

            TodoListTheme(
                theme = theme,
                colorSystem = colorSystem
            ) {
                NavGraph()
            }
        }
    }
}