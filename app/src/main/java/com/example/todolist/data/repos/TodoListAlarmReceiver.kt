package com.example.todolist.data.repos

import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.todolist.R

class TodoListAlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        val todoTitle = intent?.getStringExtra("todoTitle")

        val notification = NotificationCompat.Builder(context, "tl_notifications")
            .setSmallIcon(R.drawable.ic_todo_list)
            .setContentTitle("TodoList")
            .setContentText("$todoTitle")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(todoTitle.hashCode(), notification)
    }
}