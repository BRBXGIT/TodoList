package com.example.todolist.presentation.settings_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist.common.app_settings.AppSettingsVM
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.design_system.todo_list_strings.TodoListStrings
import com.example.todolist.presentation.settings_screen.sections.ThemePreviewsSection
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    appSettingsVM: AppSettingsVM
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = TodoListStrings.settings),
                        style = mTypography.titleMedium
                    )
                },
                navigationIcon = { 
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            painter = painterResource(id = TodoListIcons.NavigationArrowLeft),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mColors.surfaceContainerHighest
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = TodoListStrings.theme),
                    modifier = Modifier.padding(start = 16.dp)
                )

                ThemePreviewsSection(appSettingsVM)
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()
        }
    }
}