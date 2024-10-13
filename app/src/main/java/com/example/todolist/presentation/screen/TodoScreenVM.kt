package com.example.todolist.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.common.Dispatcher
import com.example.todolist.common.TodoListDispatchers
import com.example.todolist.data.db.Todo
import com.example.todolist.data.repo.MainRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoScreenVM @Inject constructor(
    private val repository: MainRepoImpl,
    @Dispatcher(TodoListDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    fun upsertTodo(todo: Todo) {
        viewModelScope.launch(dispatcherIo) {
            repository.upsertTodo(todo)
        }
    }

    fun updateTodoCompleted(id: Int, completed: Boolean) {
        viewModelScope.launch(dispatcherIo) {
            repository.updateTodoCompleted(id, completed)
        }
    }

    val allTodo = repository.getAllTodo()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )
}