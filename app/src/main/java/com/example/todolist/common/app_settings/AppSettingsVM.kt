package com.example.todolist.common.app_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.common.dispatchers.Dispatcher
import com.example.todolist.common.dispatchers.TodoListDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppSettingsVM @Inject constructor(
    private val dataStore: AppDataStore,
    @Dispatcher(TodoListDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
): ViewModel() {

    val theme = dataStore.themeFlow
    val colorSystem = dataStore.colorSystemFlow

    fun changeTheme(theme: String) {
        viewModelScope.launch(dispatcherIo) {
            dataStore.saveTheme(theme)
        }
    }

    fun changeColorSystem(colorSystem: String) {
        viewModelScope.launch(dispatcherIo) {
            dataStore.saveColorSystem(colorSystem)
        }
    }

    val tutorialCompleted = dataStore.tutorialCompletedFlow

    fun setTutorialCompleted() {
        viewModelScope.launch(dispatcherIo) {
            dataStore.saveTutorialCompleted(true)
        }
    }
}