package com.example.todolist.presentation.todo_screen.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.example.todolist.design_system.snackbars.SnackbarController
import com.example.todolist.design_system.snackbars.SnackbarEvent
import com.example.todolist.design_system.todo_list_strings.TodoListStrings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CheckPermissions(
    scope: CoroutineScope,
    context: Context
) {
    val notificationsGranted = stringResource(id = TodoListStrings.notificationsGranted)
    val notificationsDenied = stringResource(id = TodoListStrings.notificationsDenied)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { result ->
            scope.launch {
                if(result) {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = notificationsGranted
                        )
                    )
                } else {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = notificationsDenied
                        )
                    )
                }
            }
        }
    )

    val notificationPermissionGranted by remember {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    LaunchedEffect(Unit) {
        if(!notificationPermissionGranted) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}