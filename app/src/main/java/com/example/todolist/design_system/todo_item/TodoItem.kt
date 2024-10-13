package com.example.todolist.design_system.todo_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todolist.data.db.Todo
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

@Composable
fun TodoItem(
    todo: Todo,
    onTodoCompletedChange: () -> Unit,
    modifier: Modifier
) {
    Surface(
        color = mColors.surfaceContainer,
        shape = mShapes.small,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = todo.completed,
                    onCheckedChange = { onTodoCompletedChange() }
                )

                Text(
                    text = todo.title,
                    style = mTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }

            Text(
                text = todo.time,
                style = mTypography.titleMedium.copy(
                    color = mColors.secondary
                )
            )
        }
    }
}