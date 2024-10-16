package com.example.todolist.presentation.todo_screen.screen

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
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.todolist.design_system.bottom_bar.TodoScreenBottomBar
import com.example.todolist.design_system.snackbars.ObserveAsEvents
import com.example.todolist.design_system.snackbars.SnackbarController
import com.example.todolist.design_system.todo_screen_fab.TodoScreenFAB
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBar
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBarVM
import com.example.todolist.presentation.settings_screen.navigation.SettingsScreenRoute
import com.example.todolist.presentation.todo_screen.sections.CheckPermissions
import com.example.todolist.presentation.todo_screen.sections.CreateTodoBS
import com.example.todolist.presentation.todo_screen.sections.LazyColumnItem
import com.example.todolist.presentation.todo_screen.sections.TutorialSection
import com.example.todolist.ui.theme.mColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todoScreenVM: TodoScreenVM,
    todoScreenTopBarVM: TodoScreenTopBarVM,
    context: Context = LocalContext.current,
    navController: NavHostController
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
                duration = SnackbarDuration.Short,
                withDismissAction = true
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

    val topBarScrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    val bottomBarScrollBehaviour = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    var showItemsWithAlarm by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = { TodoScreenTopBar(todoScreenTopBarVM, topBarScrollBehaviour) },
        floatingActionButton = { TodoScreenFAB(onClick = { createTodoBSOpen = true }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        bottomBar = {
            TodoScreenBottomBar(
                onAlarmButtonClick = { showItemsWithAlarm = !showItemsWithAlarm },
                onlyAlarmsShowed = showItemsWithAlarm,
                scrollBehavior = bottomBarScrollBehaviour,
                onSettingsButtonClick = { navController.navigate(SettingsScreenRoute) }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(topBarScrollBehaviour.nestedScrollConnection)
            .nestedScroll(bottomBarScrollBehaviour.nestedScrollConnection)
            .background(mColors.background)
    ) { innerPadding ->
        val todoItems by todoScreenVM.allTodo.collectAsStateWithLifecycle()

        val itemsByDate = todoItems.filter { todo ->
            todo.date == selectedDate
        }

        val completedItems = itemsByDate.filter { todo ->
            todo.completed
        }
        val uncompletedItems = itemsByDate.filter { todo ->
            !todo.completed
        }
        val itemsWithAlarm = itemsByDate.filter { todo ->
            todo.alarm
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
            item {
                TutorialSection(
                    modifier = Modifier.animateItem()
                )
            }

            if(!showItemsWithAlarm) {
                if(uncompletedItems.isNotEmpty()) {
                    item {
                        Text(
                            text = "Todo: ",
                            modifier = Modifier.animateItem()
                        )
                    }

                    items(uncompletedItems, key = { it.id }) { todo ->
                        LazyColumnItem(
                            todo = todo,
                            todoScreenVM = todoScreenVM,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min)
                                .animateItem()
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
                        LazyColumnItem(
                            todo = todo,
                            todoScreenVM = todoScreenVM,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min)
                                .animateItem()
                        )
                    }
                }
            } else {
                item {
                    Text(
                        text = "Todos with alarm: ",
                        modifier = Modifier.animateItem()
                    )
                }

                items(itemsWithAlarm, key = { it.id }) { todo ->
                    LazyColumnItem(
                        todo = todo,
                        todoScreenVM = todoScreenVM,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .animateItem()
                    )
                }
            }
        }
    }
}