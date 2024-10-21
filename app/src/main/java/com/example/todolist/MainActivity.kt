package com.example.todolist

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.common.app_settings.AppSettingsVM
import com.example.todolist.common.navigation.NavGraph
import com.example.todolist.ui.theme.TodoListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_TodoList)

        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        enableEdgeToEdge()

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { true }
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            splashScreen.setKeepOnScreenCondition { false }
        }

        setContent {
            val appSettingsVM = hiltViewModel<AppSettingsVM>()
            val theme by appSettingsVM.theme.collectAsState(initial = "default")
            val colorSystem by appSettingsVM.colorSystem.collectAsState(initial = "default")

            TodoListTheme(
                theme = theme,
                colorSystem = colorSystem
            ) {
                NavGraph(appSettingsVM)
            }
        }
    }
}