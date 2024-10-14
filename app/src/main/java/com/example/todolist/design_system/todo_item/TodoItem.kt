package com.example.todolist.design_system.todo_item

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.todolist.data.db.Todo
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TodoItem(
    todo: Todo,
    onTodoCompletedChange: () -> Unit,
    modifier: Modifier,
    //Swipe actions parameters
    isRevealed: Boolean,
    actions: @Composable BoxScope.() -> Unit,
    onExpand: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    var contextMenuWidth by rememberSaveable { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = isRevealed, contextMenuWidth) {
        if(isRevealed) {
            offset.animateTo(contextMenuWidth)
        } else {
            offset.animateTo(0f)
        }
    }

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .onSizeChanged {
                    contextMenuWidth = it.width.toFloat()
                },
            contentAlignment = Alignment.Center
        ) {
            actions()
        }

        Surface(
            color = mColors.surfaceContainer,
            shape = mShapes.small,
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offset.value.roundToInt(), 0) }
                .pointerInput(contextMenuWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = (offset.value + dragAmount)
                                    .coerceIn(0f, contextMenuWidth)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value >= contextMenuWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(contextMenuWidth)
                                        onExpand()
                                    }
                                }

                                else -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                        onCollapsed()
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Checkbox(
                        checked = todo.completed,
                        onCheckedChange = { onTodoCompletedChange() }
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = todo.title,
                            style = mTypography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Text(
                            text = todo.description,
                            style = mTypography.titleSmall.copy(
                                color = mColors.tertiary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }

                Text(
                    text = todo.time,
                    style = mTypography.titleMedium.copy(
                        color = mColors.secondary
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
