package com.example.todolist.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.design_system.todo_item.TodoItem
import com.example.todolist.design_system.todo_screen_fab.TodoScreenFAB
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBar
import com.example.todolist.design_system.todo_screen_top_bar.TodoScreenTopBarVM
import com.example.todolist.presentation.sections.CreateTodoBS
import com.example.todolist.ui.theme.mColors

@Composable
fun TodoScreen(
    todoScreenVM: TodoScreenVM,
    todoScreenTopBarVM: TodoScreenTopBarVM
) {
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
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) { innerPadding ->
        val todoItems by todoScreenVM.allTodo.collectAsStateWithLifecycle()

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
            val filteredItems = todoItems.filter { todo ->
                todo.date == selectedDate
            }.sortedBy { todo ->
                todo.completed
            }
            items(filteredItems, key = { it.id }) { todo ->
                TodoItem(
                    todo = todo,
                    onTodoCompletedChange = {
                        todoScreenVM.updateTodoCompleted(todo.id, !todo.completed)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem()
                )
            }
        }
    }
}