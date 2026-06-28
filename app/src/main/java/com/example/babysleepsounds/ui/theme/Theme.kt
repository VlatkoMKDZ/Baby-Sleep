package com.example.babysleepsounds.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val BedtimeColors = darkColorScheme(
    primary = SoftLavender,
    secondary = LightPurple,
    tertiary = SoftYellowMoon,
    background = DeepMidnightBlue,
    surface = CardMidnight,
    surfaceContainer = MidnightBlue,
    surfaceContainerHigh = CardMidnight,
    primaryContainer = SoftLavender.copy(alpha = 0.24f),
    secondaryContainer = LightPurple.copy(alpha = 0.18f),
    onPrimary = TextWhite,
    onSecondary = TextWhite,
    onTertiary = DeepMidnightBlue,
    onBackground = TextWhite,
    onSurface = TextWhite,
    onSurfaceVariant = TextLightGray,
    onPrimaryContainer = TextWhite
)

@Composable
fun BabySleepSoundsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BedtimeColors,
        typography = BabySleepTypography,
        shapes = BabySleepShapes,
        content = content
    )
}
