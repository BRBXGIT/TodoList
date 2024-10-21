package com.example.todolist.presentation.info_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.design_system.todo_list_strings.TodoListStrings
import com.example.todolist.presentation.info_screen.sections.LogoSection
import com.example.todolist.presentation.info_screen.sections.SocialNetworksSection
import com.example.todolist.presentation.info_screen.sections.VersionPrivacyPolicySection
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = TodoListStrings.info),
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mColors.background)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            LogoSection()

            HorizontalDivider()

            VersionPrivacyPolicySection()

            SocialNetworksSection()
        }
    }
}