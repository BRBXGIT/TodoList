package com.example.todolist.presentation.todo_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.data.db.Todo
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBarVM
import com.example.todolist.presentation.todo_screen.screen.TodoScreenVM
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoBS(
    onDismissRequest: () -> Unit,
    todoScreenVM: TodoScreenVM,
    todoScreenTopBarVM: TodoScreenTopBarVM
) {
    val selectedDate by todoScreenTopBarVM.selectedDate.collectAsStateWithLifecycle()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("00:00") }

    val clockDialogState = rememberUseCaseState()
    ClockDialog(
        state = clockDialogState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            //This clock dialog has bug with "0", i solved it with this method
            val formattedHours = if (hours < 10) "0$hours" else "$hours"
            val formattedMinutes = if (minutes < 10) "0$minutes" else "$minutes"
            time = "$formattedHours:$formattedMinutes"
        },
        config = ClockConfig(is24HourFormat = true)
    )

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        tonalElevation = 0.dp,
        shape = mShapes.small,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { clockDialogState.show() },
                    shape = mShapes.small,
                    modifier = Modifier.weight(0.7f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = TodoListIcons.AddAlarm),
                            contentDescription = null
                        )

                        Text(
                            text = "Add time"
                        )
                    }
                }

                Text(
                    text = time,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.2f),
                    style = mTypography.titleMedium
                )
            }

            Button(
                onClick = {
                    todoScreenVM.upsertTodo(
                        Todo(
                            title = title,
                            time = time,
                            date = selectedDate,
                            description = description
                        )
                    )
                    onDismissRequest()
                },
                shape = mShapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = TodoListIcons.CheckRounded),
                        contentDescription = null
                    )

                    Text(
                        text = "Create todo"
                    )
                }
            }

            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}