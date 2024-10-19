package com.example.todolist.presentation.todo_screen.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

@Composable
fun TutorialSection(
    modifier: Modifier,
    onDeleteButtonClick: () -> Unit
) {
    Surface(
        color = mColors.tertiaryContainer,
        shape = mShapes.large,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                painter = painterResource(id = TodoListIcons.CrossFilled),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onDeleteButtonClick() }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Icon(
                    painter = painterResource(id = TodoListIcons.MagicStickFilled),
                    contentDescription = null
                )

                Text(
                    text = "To create an alarm choose time while creating todo, and after todo " +
                            "created swipe it to right, than click add alarm :)",
                    style = mTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}