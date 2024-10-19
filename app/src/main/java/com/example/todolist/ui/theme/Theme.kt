package com.example.todolist.ui.theme

import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

val lightGreenAppleScheme = lightColorScheme(
    primary = primaryGreenAppleLight,
    onPrimary = onPrimaryGreenAppleLight,
    primaryContainer = primaryContainerGreenAppleLight,
    onPrimaryContainer = onPrimaryContainerGreenAppleLight,
    secondary = secondaryGreenAppleLight,
    onSecondary = onSecondaryGreenAppleLight,
    secondaryContainer = secondaryContainerGreenAppleLight,
    onSecondaryContainer = onSecondaryContainerGreenAppleLight,
    tertiary = tertiaryGreenAppleLight,
    onTertiary = onTertiaryGreenAppleLight,
    tertiaryContainer = tertiaryContainerGreenAppleLight,
    onTertiaryContainer = onTertiaryContainerGreenAppleLight,
    error = errorGreenAppleLight,
    onError = onErrorGreenAppleLight,
    errorContainer = errorContainerGreenAppleLight,
    onErrorContainer = onErrorContainerGreenAppleLight,
    background = backgroundGreenAppleLight,
    onBackground = onBackgroundGreenAppleLight,
    surface = surfaceGreenAppleLight,
    onSurface = onSurfaceGreenAppleLight,
    surfaceVariant = surfaceVariantGreenAppleLight,
    onSurfaceVariant = onSurfaceVariantGreenAppleLight,
    outline = outlineGreenAppleLight,
    outlineVariant = outlineVariantGreenAppleLight,
    scrim = scrimGreenAppleLight,
    inverseSurface = inverseSurfaceGreenAppleLight,
    inverseOnSurface = inverseOnSurfaceGreenAppleLight,
    inversePrimary = inversePrimaryGreenAppleLight,
    surfaceDim = surfaceDimGreenAppleLight,
    surfaceBright = surfaceBrightGreenAppleLight,
    surfaceContainerLowest = surfaceContainerLowestGreenAppleLight,
    surfaceContainerLow = surfaceContainerLowGreenAppleLight,
    surfaceContainer = surfaceContainerGreenAppleLight,
    surfaceContainerHigh = surfaceContainerHighGreenAppleLight,
    surfaceContainerHighest = surfaceContainerHighestGreenAppleLight,
)

val darkGreenAppleScheme = darkColorScheme(
    primary = primaryGreenAppleDark,
    onPrimary = onPrimaryGreenAppleDark,
    primaryContainer = primaryContainerGreenAppleDark,
    onPrimaryContainer = onPrimaryContainerGreenAppleDark,
    secondary = secondaryGreenAppleDark,
    onSecondary = onSecondaryGreenAppleDark,
    secondaryContainer = secondaryContainerGreenAppleDark,
    onSecondaryContainer = onSecondaryContainerGreenAppleDark,
    tertiary = tertiaryGreenAppleDark,
    onTertiary = onTertiaryGreenAppleDark,
    tertiaryContainer = tertiaryContainerGreenAppleDark,
    onTertiaryContainer = onTertiaryContainerGreenAppleDark,
    error = errorGreenAppleDark,
    onError = onErrorGreenAppleDark,
    errorContainer = errorContainerGreenAppleDark,
    onErrorContainer = onErrorContainerGreenAppleDark,
    background = backgroundGreenAppleDark,
    onBackground = onBackgroundGreenAppleDark,
    surface = surfaceGreenAppleDark,
    onSurface = onSurfaceGreenAppleDark,
    surfaceVariant = surfaceVariantGreenAppleDark,
    onSurfaceVariant = onSurfaceVariantGreenAppleDark,
    outline = outlineGreenAppleDark,
    outlineVariant = outlineVariantGreenAppleDark,
    scrim = scrimGreenAppleDark,
    inverseSurface = inverseSurfaceGreenAppleDark,
    inverseOnSurface = inverseOnSurfaceGreenAppleDark,
    inversePrimary = inversePrimaryGreenAppleDark,
    surfaceDim = surfaceDimGreenAppleDark,
    surfaceBright = surfaceBrightGreenAppleDark,
    surfaceContainerLowest = surfaceContainerLowestGreenAppleDark,
    surfaceContainerLow = surfaceContainerLowGreenAppleDark,
    surfaceContainer = surfaceContainerGreenAppleDark,
    surfaceContainerHigh = surfaceContainerHighGreenAppleDark,
    surfaceContainerHighest = surfaceContainerHighestGreenAppleDark,
)

val lightSakuraScheme = lightColorScheme(
    primary = primarySakuraLight,
    onPrimary = onPrimarySakuraLight,
    primaryContainer = primaryContainerSakuraLight,
    onPrimaryContainer = onPrimaryContainerSakuraLight,
    secondary = secondarySakuraLight,
    onSecondary = onSecondarySakuraLight,
    secondaryContainer = secondaryContainerSakuraLight,
    onSecondaryContainer = onSecondaryContainerSakuraLight,
    tertiary = tertiarySakuraLight,
    onTertiary = onTertiarySakuraLight,
    tertiaryContainer = tertiaryContainerSakuraLight,
    onTertiaryContainer = onTertiaryContainerSakuraLight,
    error = errorSakuraLight,
    onError = onErrorSakuraLight,
    errorContainer = errorContainerSakuraLight,
    onErrorContainer = onErrorContainerSakuraLight,
    background = backgroundSakuraLight,
    onBackground = onBackgroundSakuraLight,
    surface = surfaceSakuraLight,
    onSurface = onSurfaceSakuraLight,
    surfaceVariant = surfaceVariantSakuraLight,
    onSurfaceVariant = onSurfaceVariantSakuraLight,
    outline = outlineSakuraLight,
    outlineVariant = outlineVariantSakuraLight,
    scrim = scrimSakuraLight,
    inverseSurface = inverseSurfaceSakuraLight,
    inverseOnSurface = inverseOnSurfaceSakuraLight,
    inversePrimary = inversePrimarySakuraLight,
    surfaceDim = surfaceDimSakuraLight,
    surfaceBright = surfaceBrightSakuraLight,
    surfaceContainerLowest = surfaceContainerLowestSakuraLight,
    surfaceContainerLow = surfaceContainerLowSakuraLight,
    surfaceContainer = surfaceContainerSakuraLight,
    surfaceContainerHigh = surfaceContainerHighSakuraLight,
    surfaceContainerHighest = surfaceContainerHighestSakuraLight
)

val darkSakuraScheme = darkColorScheme(
    primary = primarySakuraDark,
    onPrimary = onPrimarySakuraDark,
    primaryContainer = primaryContainerSakuraDark,
    onPrimaryContainer = onPrimaryContainerSakuraDark,
    secondary = secondarySakuraDark,
    onSecondary = onSecondarySakuraDark,
    secondaryContainer = secondaryContainerSakuraDark,
    onSecondaryContainer = onSecondaryContainerSakuraDark,
    tertiary = tertiarySakuraDark,
    onTertiary = onTertiarySakuraDark,
    tertiaryContainer = tertiaryContainerSakuraDark,
    onTertiaryContainer = onTertiaryContainerSakuraDark,
    error = errorSakuraDark,
    onError = onErrorSakuraDark,
    errorContainer = errorContainerSakuraDark,
    onErrorContainer = onErrorContainerSakuraDark,
    background = backgroundSakuraDark,
    onBackground = onBackgroundSakuraDark,
    surface = surfaceSakuraDark,
    onSurface = onSurfaceSakuraDark,
    surfaceVariant = surfaceVariantSakuraDark,
    onSurfaceVariant = onSurfaceVariantSakuraDark,
    outline = outlineSakuraDark,
    outlineVariant = outlineVariantSakuraDark,
    scrim = scrimSakuraDark,
    inverseSurface = inverseSurfaceSakuraDark,
    inverseOnSurface = inverseOnSurfaceSakuraDark,
    inversePrimary = inversePrimarySakuraDark,
    surfaceDim = surfaceDimSakuraDark,
    surfaceBright = surfaceBrightSakuraDark,
    surfaceContainerLowest = surfaceContainerLowestSakuraDark,
    surfaceContainerLow = surfaceContainerLowSakuraDark,
    surfaceContainer = surfaceContainerSakuraDark,
    surfaceContainerHigh = surfaceContainerHighSakuraDark,
    surfaceContainerHighest = surfaceContainerHighestSakuraDark,
)

@Composable
fun TodoListTheme(
    colorSystem: String,
    theme: String,
    darkTheme: Boolean = isSystemInDarkTheme(),
    context: Context = LocalContext.current,
    content: @Composable () -> Unit,
) {
    val colorScheme = when(theme) {
        "default" -> if(darkTheme) {
            when(colorSystem) {
                "default" -> darkScheme
                "dark" -> darkScheme
                "light" -> darkScheme
                "lightGreenApple" -> darkGreenAppleScheme
                "darkGreenApple" -> darkGreenAppleScheme
                "lightSakura" -> darkSakuraScheme
                "darkSakura" -> darkSakuraScheme
                else -> darkScheme
            }
        } else {
            when(colorSystem) {
                "default" -> lightScheme
                "dark" -> lightScheme
                "light" -> lightScheme
                "lightGreenApple" -> lightGreenAppleScheme
                "darkGreenApple" -> lightGreenAppleScheme
                "lightSakura" -> lightSakuraScheme
                "darkSakura" -> lightSakuraScheme
                else -> lightScheme
            }
        }
        "dark" -> when(colorSystem) {
            "default" -> darkScheme
            "dark" -> darkScheme
            "light" -> darkScheme
            "lightGreenApple" -> darkGreenAppleScheme
            "darkGreenApple" -> darkGreenAppleScheme
            "lightSakura" -> darkSakuraScheme
            "darkSakura" -> darkSakuraScheme
            else -> darkScheme
        }
        "light" -> when(colorSystem) {
            "default" -> lightScheme
            "dark" -> lightScheme
            "light" -> lightScheme
            "lightGreenApple" -> lightGreenAppleScheme
            "darkGreenApple" -> lightGreenAppleScheme
            "lightSakura" -> lightSakuraScheme
            "darkSakura" -> lightSakuraScheme
            else -> lightScheme
        }
        else -> if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if(darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        } else {
            if(darkTheme) {
                darkScheme
            } else {
                lightScheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val mColors @Composable get() = MaterialTheme.colorScheme
val mShapes @Composable get() = MaterialTheme.shapes
val mTypography @Composable get() = MaterialTheme.typography