package com.example.todolist.data.repo

import com.example.todolist.data.db.Todo
import com.example.todolist.data.db.TodoDao
import com.example.todolist.domain.MainRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val todoDao: TodoDao
): MainRepo {

    override suspend fun upsertTodo(todo: Todo) {
        todoDao.upsertTodo(todo)
    }

    override suspend fun updateTodoCompleted(id: Int, completed: Boolean) {
        todoDao.updateTodoCompleted(id, completed)
    }

    override suspend fun updateAlarm(id: Int, alarm: Boolean) {
        todoDao.updateAlarm(id, alarm)
    }

    override fun getAllTodo(): Flow<List<Todo>> {
        return  todoDao.getAllTodo()
    }

    override fun getTodoById(id: Int): Flow<Todo> {
        return todoDao.getTodoById(id)
    }
}