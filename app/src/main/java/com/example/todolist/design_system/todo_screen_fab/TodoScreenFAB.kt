package com.example.todolist.design_system.todo_screen_fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolist.design_system.todo_list_icons.TodoListIcons

@Composable
fun TodoScreenFAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        elevation = FloatingActionButtonDefaults.elevation(
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp,
            defaultElevation = 0.dp
        )
    ) {
        Icon(
            painter = painterResource(id = TodoListIcons.PlusFilled),
            contentDescription = null
        )
    }
}