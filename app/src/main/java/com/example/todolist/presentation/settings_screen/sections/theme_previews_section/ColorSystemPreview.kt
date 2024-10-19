package com.example.todolist.presentation.settings_screen.sections.theme_previews_section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

data class ColorSystemPreviewColors(
    val background: Color,
    val surfaceContainerHighest: Color,
    val surfaceContainer: Color,
    val onSecondaryContainer: Color,
    val primaryContainer: Color,
    val name: String,
    val previewName: String
)

@Composable
fun ColorSystemPreview(
    background: Color,
    surfaceContainerHighest: Color,
    surfaceContainer: Color,
    onSecondaryContainer: Color,
    primaryContainer: Color,
    chosenColorSystem: String,
    name: String,
    previewName: String,
    onClick: () -> Unit
) {
    val chosen = chosenColorSystem == name

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(110.dp)
                .height(180.dp)
                .background(
                    shape = mShapes.small,
                    color = background
                )
                .border(
                    width = if (chosen) 2.dp else 1.dp,
                    color = mColors.secondary,
                    shape = mShapes.small
                )
                .clickable {
                    onClick()
                }
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        color = surfaceContainerHighest,
                        shape = mShapes.small.copy(
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp)
                        )
                    )
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(
                    vertical = 28.dp,
                    horizontal = 4.dp
                )
            ) {
                items(3) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(
                                shape = mShapes.extraSmall,
                                color = surfaceContainer
                            )
                    )
                }
            }

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        color = surfaceContainerHighest,
                        shape = mShapes.small.copy(
                            topEnd = CornerSize(0.dp),
                            topStart = CornerSize(0.dp)
                        )
                    )
                    .padding(end = 4.dp)
            ) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        horizontal = 4.dp
                    )
                ) {
                    items(2) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(
                                    shape = CircleShape,
                                    color = onSecondaryContainer
                                )
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.CenterEnd)
                        .background(
                            shape = mShapes.extraSmall,
                            color = primaryContainer
                        )
                )
            }
        }

        Text(
            text = previewName,
            style = mTypography.labelMedium
        )
    }
}