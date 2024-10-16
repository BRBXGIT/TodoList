package com.example.todolist.presentation.todo_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

@Composable
fun TutorialSection(
    modifier: Modifier
) {
    Surface(
        color = mColors.tertiaryContainer,
        shape = mShapes.large,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = TodoListIcons.MagicStickFilled),
                contentDescription = null
            )

            Text(
                text = "To create alarm choose time while creating todo, and after todo " +
                        "created swipe it to right, than click add alarm :)",
                style = mTypography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}