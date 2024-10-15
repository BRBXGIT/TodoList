package com.example.todolist.design_system.bottom_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.ui.theme.mColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreenBottomBar(
    onAlarmButtonClick: () -> Unit,
    onlyAlarmsShowed: Boolean,
    scrollBehavior: BottomAppBarScrollBehavior,
    onSettingsButtonClick: () -> Unit
) {
    BottomAppBar(
        containerColor = mColors.surfaceContainerHighest,
        scrollBehavior = scrollBehavior,
    ) {
        AnimatedVisibility(
            visible = scrollBehavior.state.heightOffset == 0f,
            enter = fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 2 },
            exit = fadeOut(tween(300)) + slideOutVertically(tween(300)) { it / 2 }
        ) {
            IconButton(
                onClick = { onSettingsButtonClick() },
            ) {
                Icon(
                    painter = painterResource(id = TodoListIcons.Settings),
                    contentDescription = null,
                )
            }
        }

        AnimatedVisibility(
            visible = scrollBehavior.state.heightOffset == 0f,
            enter = fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 2 },
            exit = fadeOut(tween(300)) + slideOutVertically(tween(300)) { it / 2 }
        ) {
            IconButton(
                onClick = { onAlarmButtonClick() }
            ) {
                if(onlyAlarmsShowed) {
                    Icon(
                        painter = painterResource(id = TodoListIcons.AlarmCreateFilled),
                        contentDescription = null
                    )
                } else {
                    Icon(
                        painter = painterResource(id = TodoListIcons.AlarmCreated),
                        contentDescription = null
                    )
                }
            }
        }
    }
}