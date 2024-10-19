package com.example.todolist.presentation.settings_screen.sections.theme_previews_section

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.common.app_settings.ThemeVM
import com.example.todolist.ui.theme.darkGreenAppleScheme
import com.example.todolist.ui.theme.darkSakuraScheme
import com.example.todolist.ui.theme.darkScheme
import com.example.todolist.ui.theme.lightGreenAppleScheme
import com.example.todolist.ui.theme.lightSakuraScheme
import com.example.todolist.ui.theme.lightScheme
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

@Composable
fun ThemePreviewsSection(
    themeVM: ThemeVM = hiltViewModel<ThemeVM>()
) {
    val chosenTheme by themeVM.theme.collectAsState(initial = "default")
    val chosenColorSystem by themeVM.colorSystem.collectAsState(initial = "default")

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(mShapes.small)
                .border(
                    width = 1.dp,
                    color = mColors.secondary,
                    shape = mShapes.small
                )
        ) {
            ThemeElement(
                text = "default",
                onClick = { themeVM.changeTheme("default") },
                chosenTheme = chosenTheme
            )

            ThemeElement(
                text = "light",
                onClick = { themeVM.changeTheme("light") },
                chosenTheme = chosenTheme
            )

            ThemeElement(
                text = "dark",
                onClick = { themeVM.changeTheme("dark") },
                chosenTheme = chosenTheme
            )

            ThemeElement(
                text = "dynamic",
                onClick = { themeVM.changeTheme("dynamic") },
                chosenTheme = chosenTheme
            )
        }

        ColorSystemElements(
            chosenTheme = chosenTheme,
            onColorSystemClick = { themeVM.changeColorSystem(it) },
            chosenColorSystem = chosenColorSystem
        )
    }
}

@Composable
fun RowScope.ThemeElement(
    text: String,
    onClick: () -> Unit,
    chosenTheme: String
) {
    val chosen = chosenTheme == text
    val chosenColor by animateColorAsState(
        targetValue = if(chosen) mColors.primaryContainer else Color.Transparent,
        label = "Animated color"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(chosenColor)
            .clickable {
                onClick()
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = mTypography.labelLarge.copy(
                color = if(chosen) mColors.onPrimaryContainer else mColors.onBackground,
                fontWeight = if(chosen) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

@Composable
fun ColorSystemElements(
    chosenTheme: String,
    chosenColorSystem: String,
    onColorSystemClick: (String) -> Unit
) {
    val darkColorSystems = listOf(
        ColorSystemPreviewColors(
            background = darkScheme.background,
            surfaceContainerHighest = darkScheme.surfaceContainerHighest,
            surfaceContainer = darkScheme.surfaceContainer,
            onSecondaryContainer = darkScheme.onSecondaryContainer,
            primaryContainer = darkScheme.primaryContainer,
            name = "darkScheme"
        ),
        ColorSystemPreviewColors(
            background = darkGreenAppleScheme.background,
            surfaceContainerHighest = darkGreenAppleScheme.surfaceContainerHighest,
            surfaceContainer = darkGreenAppleScheme.surfaceContainer,
            onSecondaryContainer = darkGreenAppleScheme.onSecondaryContainer,
            primaryContainer = darkGreenAppleScheme.primaryContainer,
            name = "darkGreenApple"
        ),
        ColorSystemPreviewColors(
            background = darkSakuraScheme.background,
            surfaceContainerHighest = darkSakuraScheme.surfaceContainerHighest,
            surfaceContainer = darkSakuraScheme.surfaceContainer,
            onSecondaryContainer = darkSakuraScheme.onSecondaryContainer,
            primaryContainer = darkSakuraScheme.primaryContainer,
            name = "darkSakura"
        )
    )

    val lightColorSystems = listOf(
        ColorSystemPreviewColors(
            background = lightScheme.background,
            surfaceContainerHighest = lightScheme.surfaceContainerHighest,
            surfaceContainer = lightScheme.surfaceContainer,
            onSecondaryContainer = lightScheme.onSecondaryContainer,
            primaryContainer = lightScheme.primaryContainer,
            name = "light"
        ),
        ColorSystemPreviewColors(
            background = lightGreenAppleScheme.background,
            surfaceContainerHighest = lightGreenAppleScheme.surfaceContainerHighest,
            surfaceContainer = lightGreenAppleScheme.surfaceContainer,
            onSecondaryContainer = lightGreenAppleScheme.onSecondaryContainer,
            primaryContainer = lightGreenAppleScheme.primaryContainer,
            name = "lightGreenApple"
        ),
        ColorSystemPreviewColors(
            background = lightSakuraScheme.background,
            surfaceContainerHighest = lightSakuraScheme.surfaceContainerHighest,
            surfaceContainer = lightSakuraScheme.surfaceContainer,
            onSecondaryContainer = lightSakuraScheme.onSecondaryContainer,
            primaryContainer = lightSakuraScheme.primaryContainer,
            name = "lightSakura"
        )
    )

    val darkThemeBySystem = isSystemInDarkTheme()
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        )
    ) {
        when(chosenTheme) {
            "default" -> if(darkThemeBySystem) {
                items(darkColorSystems) { colorSystem ->
                    ColorSystemPreview(
                        background = colorSystem.background,
                        surfaceContainerHighest = colorSystem.surfaceContainerHighest,
                        surfaceContainer = colorSystem.surfaceContainer,
                        onSecondaryContainer = colorSystem.onSecondaryContainer,
                        primaryContainer = colorSystem.primaryContainer,
                        chosenColorSystem = chosenColorSystem,
                        name = colorSystem.name,
                        onClick = { onColorSystemClick(colorSystem.name) }
                    )
                }
            } else {
                items(lightColorSystems) { colorSystem ->
                    ColorSystemPreview(
                        background = colorSystem.background,
                        surfaceContainerHighest = colorSystem.surfaceContainerHighest,
                        surfaceContainer = colorSystem.surfaceContainer,
                        onSecondaryContainer = colorSystem.onSecondaryContainer,
                        primaryContainer = colorSystem.primaryContainer,
                        chosenColorSystem = chosenColorSystem,
                        name = colorSystem.name,
                        onClick = { onColorSystemClick(colorSystem.name) }
                    )
                }
            }
            "light" -> {
                items(lightColorSystems) { colorSystem ->
                    ColorSystemPreview(
                        background = colorSystem.background,
                        surfaceContainerHighest = colorSystem.surfaceContainerHighest,
                        surfaceContainer = colorSystem.surfaceContainer,
                        onSecondaryContainer = colorSystem.onSecondaryContainer,
                        primaryContainer = colorSystem.primaryContainer,
                        chosenColorSystem = chosenColorSystem,
                        name = colorSystem.name,
                        onClick = { onColorSystemClick(colorSystem.name) }
                    )
                }
            }
            "dark" -> {
                items(darkColorSystems) { colorSystem ->
                    ColorSystemPreview(
                        background = colorSystem.background,
                        surfaceContainerHighest = colorSystem.surfaceContainerHighest,
                        surfaceContainer = colorSystem.surfaceContainer,
                        onSecondaryContainer = colorSystem.onSecondaryContainer,
                        primaryContainer = colorSystem.primaryContainer,
                        chosenColorSystem = chosenColorSystem,
                        name = colorSystem.name,
                        onClick = { onColorSystemClick(colorSystem.name) }
                    )
                }
            }
        }
    }
}