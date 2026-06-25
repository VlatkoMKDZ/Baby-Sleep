package com.example.babysleepsounds.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF6F63D9),
    secondary = Color(0xFF7D6C8F),
    tertiary = Color(0xFF9B6A4E),
    background = Color(0xFFFFFBFF),
    surface = Color(0xFFF7F1FF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFC9BEFF),
    secondary = Color(0xFFD0C1DA),
    tertiary = Color(0xFFFFB68F),
    background = Color(0xFF141218),
    surface = Color(0xFF211F26)
)

@Composable
fun BabySleepSoundsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
