package com.example.todolist.presentation.settings_screen.sections

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.design_system.snackbars.SnackbarController
import com.example.todolist.design_system.snackbars.SnackbarEvent
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mTypography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun VersionPrivacyPolicySection(
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val clipboardManager = LocalClipboardManager.current
    val versionText = "Version"
    val versionCodeText = "Stable 1.0.0 (31.08.2024)"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                clipboardManager.setText(AnnotatedString("$versionText $versionCodeText"))
                scope.launch {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Copied to clipboard"
                        )
                    )
                }
            }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = versionText,
                style = mTypography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = versionCodeText,
                style = mTypography.labelLarge.copy(
                    color = mColors.secondary
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/BRBXGIT")
                    )
                )
            }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Privacy policy",
            style = mTypography.bodyLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}