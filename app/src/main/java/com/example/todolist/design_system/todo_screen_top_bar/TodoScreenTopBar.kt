package com.example.todolist.design_system.todo_screen_top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreenTopBar(
    viewModel: TodoScreenTopBarVM,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val date by viewModel.selectedDate.collectAsStateWithLifecycle()

    val calendarDialogState = rememberUseCaseState()
    CenterAlignedTopAppBar(
        title = {
            Calendar(
                onSetDateClick = { viewModel.setDate(it) },
                calendarDialogState = calendarDialogState
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { viewModel.dateMinusDay() }
                ) {
                    Icon(
                        painter = painterResource(id = TodoListIcons.ArrowLeft),
                        contentDescription = null
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .clip(mShapes.small)
                        .clickable { calendarDialogState.show() }
                ) {
                    Icon(
                        painter = painterResource(id = TodoListIcons.Calendar),
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 4.dp,
                            bottom = 4.dp,
                            top = 4.dp
                        )
                    )

                    Text(
                        text = date,
                        modifier = Modifier.padding(
                            end = 4.dp,
                            bottom = 4.dp,
                            top = 4.dp
                        ),
                        style = mTypography.titleMedium
                    )
                }

                IconButton(
                    onClick = { viewModel.datePlusDay() }
                ) {
                    Icon(
                        painter = painterResource(id = TodoListIcons.ArrowRight),
                        contentDescription = null
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            scrolledContainerColor = mColors.surfaceContainerHighest
        )
    )
}