package com.example.todolist.data.repos

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject

class TodoListAlarmManager @Inject constructor(
    private val alarmManager: AlarmManager,
    private val repository: MainRepoImpl,
    private val context: Context
) {
    suspend fun scheduleTodoAlarm(id: Int) {
        repository.getTodoById(id).collect { todo ->
            val intent = Intent(context, TodoListAlarmReceiver::class.java).apply {
                putExtra("todoTitle", todo.title)
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                todo.id,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val hours = todo.time.split(":")[0].toInt()
            val minutes = todo.time.split(":")[1].toInt()

            val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
            val dayOfYear = LocalDate.parse(todo.date, formatter).dayOfYear
            val year = LocalDate.parse(todo.date, formatter).year

            val calendar = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.DAY_OF_YEAR, dayOfYear)
                set(Calendar.HOUR_OF_DAY, hours)
                set(Calendar.MINUTE, minutes)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    suspend fun cancelTodoAlarm(id: Int) {
        repository.getTodoById(id).collect { todo ->
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                todo.id,
                Intent(context, TodoListAlarmReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }
}