package com.example.todolist.presentation.todo_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.todolist.R
import com.example.todolist.ui.theme.mTypography

@Composable
fun EmptyTodoListSection(
    modifier: Modifier,
) {
    var visible by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_todo_list_animation))

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(500))
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Column {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(200.dp),
                    iterations = LottieConstants.IterateForever
                )

                Text(
                    text = "Nothing here, add todo",
                    style = mTypography.bodyLarge.copy(
                        lineBreak = LineBreak.Paragraph
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}