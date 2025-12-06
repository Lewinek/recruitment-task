package com.example.recruitment_task.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Accent,
    onPrimary = AccentForeground,
    secondary = Secondary,
    onSecondary = SecondaryForeground,
    background = Background,
    onBackground = Foreground,
    surface = Card,
    onSurface = Foreground,
    surfaceVariant = Secondary,
    onSurfaceVariant = MutedForeground,
    outline = Border,
    error = Color(0xFFEF4444),
    onError = Color(0xFFFFFFFF)
)

private val DarkColorScheme = darkColorScheme(
    primary = Accent,
    onPrimary = Color(0xFF0F1419),
    secondary = SecondaryDark,
    onSecondary = ForegroundDark,
    background = BackgroundDark,
    onBackground = ForegroundDark,
    surface = CardDark,
    onSurface = ForegroundDark,
    surfaceVariant = SecondaryDark,
    onSurfaceVariant = Muted,
    outline = BorderDark,
    error = Color(0xFFDC2626),
    onError = ForegroundDark
)

@Composable
fun RecruitmenttaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}