package com.example.todolist.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val todoListDispatcher: TodoListDispatchers)

enum class TodoListDispatchers {
    Default,
    IO
}