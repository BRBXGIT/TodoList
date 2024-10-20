package com.example.todolist.presentation.settings_screen.sections

import android.os.Build
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todolist.common.app_settings.AppSettingsVM
import com.example.todolist.design_system.todo_list_strings.TodoListStrings
import com.example.todolist.ui.theme.darkAquamarineScheme
import com.example.todolist.ui.theme.darkDaiquiriScheme
import com.example.todolist.ui.theme.darkGreenAppleScheme
import com.example.todolist.ui.theme.darkSakuraScheme
import com.example.todolist.ui.theme.darkScheme
import com.example.todolist.ui.theme.darkTacosScheme
import com.example.todolist.ui.theme.lightAquamarineScheme
import com.example.todolist.ui.theme.lightDaiquiriScheme
import com.example.todolist.ui.theme.lightGreenAppleScheme
import com.example.todolist.ui.theme.lightSakuraScheme
import com.example.todolist.ui.theme.lightScheme
import com.example.todolist.ui.theme.lightTacosScheme
import com.example.todolist.ui.theme.mColors
import com.example.todolist.ui.theme.mShapes
import com.example.todolist.ui.theme.mTypography

@Composable
fun ThemePreviewsSection(
    appSettingsVM: AppSettingsVM
) {
    val chosenTheme by appSettingsVM.theme.collectAsState(initial = "default")
    val chosenColorSystem by appSettingsVM.colorSystem.collectAsState(initial = "default")

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
                type = "default",
                text = stringResource(id = TodoListStrings.settingsDefaultTheme),
                onClick = { appSettingsVM.changeTheme("default") },
                chosenTheme = chosenTheme
            )

            ThemeElement(
                type = "light",
                text = stringResource(id = TodoListStrings.settingsLightTheme),
                onClick = { appSettingsVM.changeTheme("light") },
                chosenTheme = chosenTheme
            )

            ThemeElement(
                type = "dark",
                text = stringResource(id = TodoListStrings.settingsDarkTheme),
                onClick = { appSettingsVM.changeTheme("dark") },
                chosenTheme = chosenTheme
            )

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ThemeElement(
                    type = "dynamic",
                    text = stringResource(id = TodoListStrings.settingsDynamicTheme),
                    onClick = { appSettingsVM.changeTheme("dynamic") },
                    chosenTheme = chosenTheme
                )
            }
        }

        ColorSystemElements(
            chosenTheme = chosenTheme,
            onColorSystemClick = { appSettingsVM.changeColorSystem(it) },
            chosenColorSystem = chosenColorSystem
        )
    }
}

@Composable
fun RowScope.ThemeElement(
    //Light, dark, etc.
    type: String,
    text: String,
    onClick: () -> Unit,
    chosenTheme: String
) {
    val chosen = chosenTheme == type
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
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = mTypography.labelLarge.copy(
                color = if(chosen) mColors.onPrimaryContainer else mColors.onBackground,
                fontWeight = if(chosen) FontWeight.Bold else FontWeight.Normal
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
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
            name = "darkScheme",
            previewName = stringResource(id = TodoListStrings.themeLavender)
        ),
        ColorSystemPreviewColors(
            background = darkGreenAppleScheme.background,
            surfaceContainerHighest = darkGreenAppleScheme.surfaceContainerHighest,
            surfaceContainer = darkGreenAppleScheme.surfaceContainer,
            onSecondaryContainer = darkGreenAppleScheme.onSecondaryContainer,
            primaryContainer = darkGreenAppleScheme.primaryContainer,
            name = "darkGreenApple",
            previewName = stringResource(id = TodoListStrings.themeGreenApple)
        ),
        ColorSystemPreviewColors(
            background = darkSakuraScheme.background,
            surfaceContainerHighest = darkSakuraScheme.surfaceContainerHighest,
            surfaceContainer = darkSakuraScheme.surfaceContainer,
            onSecondaryContainer = darkSakuraScheme.onSecondaryContainer,
            primaryContainer = darkSakuraScheme.primaryContainer,
            name = "darkSakura",
            previewName = stringResource(id = TodoListStrings.themeSakura)
        ),
        ColorSystemPreviewColors(
            background = darkAquamarineScheme.background,
            surfaceContainerHighest = darkAquamarineScheme.surfaceContainerHighest,
            surfaceContainer = darkAquamarineScheme.surfaceContainer,
            onSecondaryContainer = darkAquamarineScheme.onSecondaryContainer,
            primaryContainer = darkAquamarineScheme.primaryContainer,
            name = "darkAquamarine",
            previewName = stringResource(id = TodoListStrings.themeAquamarine)
        ),
        ColorSystemPreviewColors(
            background = darkDaiquiriScheme.background,
            surfaceContainerHighest = darkDaiquiriScheme.surfaceContainerHighest,
            surfaceContainer = darkDaiquiriScheme.surfaceContainer,
            onSecondaryContainer = darkDaiquiriScheme.onSecondaryContainer,
            primaryContainer = darkDaiquiriScheme.primaryContainer,
            name = "darkDaiquiri",
            previewName = stringResource(id = TodoListStrings.themeDaiquiri)
        ),
        ColorSystemPreviewColors(
            background = darkTacosScheme.background,
            surfaceContainerHighest = darkTacosScheme.surfaceContainerHighest,
            surfaceContainer = darkTacosScheme.surfaceContainer,
            onSecondaryContainer = darkTacosScheme.onSecondaryContainer,
            primaryContainer = darkTacosScheme.primaryContainer,
            name = "darkTacos",
            previewName = stringResource(id = TodoListStrings.themeTacos)
        ),
    )

    val lightColorSystems = listOf(
        ColorSystemPreviewColors(
            background = lightScheme.background,
            surfaceContainerHighest = lightScheme.surfaceContainerHighest,
            surfaceContainer = lightScheme.surfaceContainer,
            onSecondaryContainer = lightScheme.onSecondaryContainer,
            primaryContainer = lightScheme.primaryContainer,
            name = "light",
            previewName = stringResource(id = TodoListStrings.themeLavender)
        ),
        ColorSystemPreviewColors(
            background = lightGreenAppleScheme.background,
            surfaceContainerHighest = lightGreenAppleScheme.surfaceContainerHighest,
            surfaceContainer = lightGreenAppleScheme.surfaceContainer,
            onSecondaryContainer = lightGreenAppleScheme.onSecondaryContainer,
            primaryContainer = lightGreenAppleScheme.primaryContainer,
            name = "lightGreenApple",
            previewName = stringResource(id = TodoListStrings.themeGreenApple)
        ),
        ColorSystemPreviewColors(
            background = lightSakuraScheme.background,
            surfaceContainerHighest = lightSakuraScheme.surfaceContainerHighest,
            surfaceContainer = lightSakuraScheme.surfaceContainer,
            onSecondaryContainer = lightSakuraScheme.onSecondaryContainer,
            primaryContainer = lightSakuraScheme.primaryContainer,
            name = "lightSakura",
            previewName = stringResource(id = TodoListStrings.themeSakura)
        ),
        ColorSystemPreviewColors(
            background = lightAquamarineScheme.background,
            surfaceContainerHighest = lightAquamarineScheme.surfaceContainerHighest,
            surfaceContainer = lightAquamarineScheme.surfaceContainer,
            onSecondaryContainer = lightAquamarineScheme.onSecondaryContainer,
            primaryContainer = lightAquamarineScheme.primaryContainer,
            name = "lightAquamarine",
            previewName = stringResource(id = TodoListStrings.themeAquamarine)
        ),
        ColorSystemPreviewColors(
            background = lightDaiquiriScheme.background,
            surfaceContainerHighest = lightDaiquiriScheme.surfaceContainerHighest,
            surfaceContainer = lightDaiquiriScheme.surfaceContainer,
            onSecondaryContainer = lightDaiquiriScheme.onSecondaryContainer,
            primaryContainer = lightDaiquiriScheme.primaryContainer,
            name = "lightDaiquiri",
            previewName = stringResource(id = TodoListStrings.themeDaiquiri)
        ),
        ColorSystemPreviewColors(
            background = lightTacosScheme.background,
            surfaceContainerHighest = lightTacosScheme.surfaceContainerHighest,
            surfaceContainer = lightTacosScheme.surfaceContainer,
            onSecondaryContainer = lightTacosScheme.onSecondaryContainer,
            primaryContainer = lightTacosScheme.primaryContainer,
            name = "lightTacos",
            previewName = stringResource(id = TodoListStrings.themeTacos)
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
                        previewName = colorSystem.previewName,
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
                        previewName = colorSystem.previewName,
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
                        previewName = colorSystem.previewName,
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
                        previewName = colorSystem.previewName,
                        onClick = { onColorSystemClick(colorSystem.name) }
                    )
                }
            }
        }
    }
}