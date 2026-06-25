package com.example.babysleepsounds.domain.model

data class SleepSoundsUiState(
    val sounds: List<SoundPlaybackState> = emptyList(),
    val selectedTimerOption: SleepTimerOption? = null,
    val isTimerPreviewActive: Boolean = false
)
