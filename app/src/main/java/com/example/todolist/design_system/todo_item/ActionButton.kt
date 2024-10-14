package com.example.todolist.design_system.todo_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

@Composable
fun ActionButton(
    icon: Int,
    onClick: () -> Unit,
    text: String
) {
    Surface(
        modifier = Modifier.fillMaxHeight(),
        color = mColors.secondaryContainer,
        shape = mShapes.small,
        onClick = { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .padding(start = 8.dp)
            )

            Text(
                text = text,
                style = mTypography.labelLarge,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}