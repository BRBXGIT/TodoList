package com.example.todolist.presentation.todo_screen.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.todolist.data.db.Todo
import com.example.todolist.design_system.snackbars.SnackbarController
import com.example.todolist.design_system.snackbars.SnackbarEvent
import com.example.todolist.design_system.todo_item.ActionButton
import com.example.todolist.design_system.todo_item.TodoItem
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.presentation.todo_screen.screen.TodoScreenVM
import com.example.todolist.ui.theme.mColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LazyColumnItem(
    todo: Todo,
    todoScreenVM: TodoScreenVM,
    scope: CoroutineScope = rememberCoroutineScope(),
    modifier: Modifier
) {
    var isRevealed by rememberSaveable { mutableStateOf(false) }
    TodoItem(
        todo = todo,
        onTodoCompletedChange = {
            todoScreenVM.updateTodoCompleted(todo.id, !todo.completed)
        },
        modifier = modifier,
        isRevealed = isRevealed,
        actions = {
            if(todo.alarm) {
                ActionButton(
                    icon = TodoListIcons.BinFilled,
                    onClick = {
                        isRevealed = false
                        todoScreenVM.updateAlarmStatus(todo.id, false)
                        scope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = "Alarm removed"
                                )
                            )
                        }
                    },
                    text = "Remove alarm",
                    color = mColors.tertiaryContainer
                )
            } else {
                ActionButton(
                    icon = TodoListIcons.AddAlarmFilled,
                    onClick = {
                        isRevealed = false
                        todoScreenVM.updateAlarmStatus(todo.id, true)
                        scope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = "Alarm added"
                                )
                            )
                        }
                    },
                    text = "Add alarm",
                    color = mColors.secondaryContainer
                )
            }
        },
        onExpand = { isRevealed = true },
        onCollapsed = { isRevealed = false }
    )
}