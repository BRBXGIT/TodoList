package com.example.todolist.design_system.bottom_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
    onSettingsButtonClick: () -> Unit,
    onInfoButtonClick: () -> Unit
) {
    BottomAppBar(
        containerColor = mColors.surfaceContainerHighest,
        scrollBehavior = scrollBehavior,
    ) {
        AnimatedVisibility(
            visible = scrollBehavior.state.heightOffset != scrollBehavior.state.heightOffsetLimit,
            enter = fadeIn(tween(500)) + slideInVertically(tween(500)) { it / 2 },
            exit = ExitTransition.None
        ) {
            IconButton(
                onClick = { onInfoButtonClick() },
            ) {
                Icon(
                    painter = painterResource(id = TodoListIcons.Info),
                    contentDescription = null,
                )
            }
        }

        AnimatedVisibility(
            visible = scrollBehavior.state.heightOffset != scrollBehavior.state.heightOffsetLimit,
            enter = fadeIn(tween(500)) + slideInVertically(tween(500)) { it / 2 },
            exit = ExitTransition.None
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
            visible = scrollBehavior.state.heightOffset != scrollBehavior.state.heightOffsetLimit,
            enter = fadeIn(tween(700)) + slideInVertically(tween(700)) { it / 2 },
            exit = ExitTransition.None
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