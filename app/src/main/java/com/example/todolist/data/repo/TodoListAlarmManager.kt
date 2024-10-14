package com.example.todolist.data.repo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class TodoListAlarmManager @Inject constructor(
    private val alarmManager: AlarmManager,
    private val repository: MainRepoImpl,
    private val context: Context
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun scheduleTodoAlarm(id: Int) {
        coroutineScope.launch {
            repository.getTodoById(id).collect { todo ->
                val intent = Intent(context, TodoListAlarmReceiver::class.java).apply {
                    putExtra("todoTitle", todo.title)
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    todo.id.hashCode(),
                    intent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )

                //Cancel alarm if it exists, doesn't work
                alarmManager.cancel(pendingIntent)

                val hours = todo.time.split(":")[0].take(2).toInt()
                val minutes = todo.time.split(":")[1].take(2).toInt()
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hours)
                    set(Calendar.MINUTE, minutes)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                    if(timeInMillis <= System.currentTimeMillis()) {
                        add(Calendar.DAY_OF_MONTH, 1)
                    }
                }

                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    fun cancelTodoAlarm(id: Int) {
        coroutineScope.launch {
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
}