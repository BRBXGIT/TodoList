package com.example.todolist.presentation.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.design_system.snackbars.ObserveAsEvents
import com.example.todolist.design_system.snackbars.SnackbarController
import com.example.todolist.design_system.snackbars.SnackbarEvent
import com.example.todolist.design_system.todo_item.ActionButton
import com.example.todolist.design_system.todo_item.TodoItem
import com.example.todolist.design_system.todo_list_icons.TodoListIcons
import com.example.todolist.design_system.todo_screen_fab.TodoScreenFAB
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBar
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBarVM
import com.example.todolist.presentation.sections.CheckPermissions
import com.example.todolist.presentation.sections.CreateTodoBS
import com.example.todolist.ui.theme.mColors
import kotlinx.coroutines.launch

@Composable
fun TodoScreen(
    todoScreenVM: TodoScreenVM,
    todoScreenTopBarVM: TodoScreenTopBarVM,
    context: Context = LocalContext.current
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    CheckPermissions(
        scope = scope,
        context = context
    )

    ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )

            if(result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    val selectedDate by todoScreenTopBarVM.selectedDate.collectAsStateWithLifecycle()
    var createTodoBSOpen by rememberSaveable { mutableStateOf(false) }
    if(createTodoBSOpen) {
        CreateTodoBS(
            onDismissRequest = { createTodoBSOpen = false },
            todoScreenVM = todoScreenVM,
            todoScreenTopBarVM = todoScreenTopBarVM
        )
    }

    Scaffold(
        topBar = { TodoScreenTopBar(todoScreenTopBarVM) },
        floatingActionButton = { TodoScreenFAB(onClick = { createTodoBSOpen = true }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) { innerPadding ->
        val todoItems by todoScreenVM.allTodo.collectAsStateWithLifecycle()

        val completedItems = todoItems.filter { todo ->
            todo.date == selectedDate && todo.completed
        }
        val uncompletedItems = todoItems.filter { todo ->
            todo.date == selectedDate && !todo.completed
        }

        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if(uncompletedItems.isNotEmpty()) {
                item {
                    Text(
                        text = "Todo: ",
                        modifier = Modifier.animateItem()
                    )
                }

                items(uncompletedItems, key = { it.id }) { todo ->
                    var isRevealed by rememberSaveable { mutableStateOf(false) }
                    TodoItem(
                        todo = todo,
                        onTodoCompletedChange = {
                            todoScreenVM.updateTodoCompleted(todo.id, !todo.completed)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .animateItem(),
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
            }

            if(completedItems.isNotEmpty()) {
                item {
                    Text(
                        text = "Completed: ",
                        modifier = Modifier.animateItem()
                    )
                }

                items(completedItems, key = { it.id }) { todo ->
                    var isRevealed by rememberSaveable { mutableStateOf(false) }
                    TodoItem(
                        todo = todo,
                        onTodoCompletedChange = {
                            todoScreenVM.updateTodoCompleted(todo.id, !todo.completed)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .animateItem(),
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
            }
        }
    }
}