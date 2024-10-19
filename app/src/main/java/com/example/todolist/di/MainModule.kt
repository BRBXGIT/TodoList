package com.example.todolist.di

import android.app.AlarmManager
import android.app.Service
import android.content.Context
import androidx.room.Room
import com.example.todolist.data.db.TodoDao
import com.example.todolist.data.db.TodoDb
import com.example.todolist.data.repos.MainRepoImpl
import com.example.todolist.data.repos.TodoListAlarmManager
import com.example.todolist.domain.MainRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext context: Context): TodoDao {
        return Room.databaseBuilder(
            context = context,
            klass = TodoDb::class.java,
            name = "TodoDb"
        ).build().todoDao()
    }

    @Provides
    @Singleton
    fun provideMainRepo(todoDao: TodoDao): MainRepo {
        return MainRepoImpl(todoDao)
    }

    @Provides
    @Singleton
    fun provideAlarmManager(@ApplicationContext context: Context): AlarmManager {
        return context.getSystemService(Service.ALARM_SERVICE) as AlarmManager
    }

    @Provides
    @Singleton
    fun provideTodoListAlarmManager(
        alarmManager: AlarmManager,
        mainRepoImpl: MainRepoImpl,
        @ApplicationContext context: Context
    ): TodoListAlarmManager {
        return TodoListAlarmManager(
            alarmManager = alarmManager,
            repository = mainRepoImpl,
            context = context
        )
    }
}