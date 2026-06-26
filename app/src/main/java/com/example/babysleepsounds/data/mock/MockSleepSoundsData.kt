package com.example.babysleepsounds.data.mock

import com.example.babysleepsounds.domain.model.SleepSound
import com.example.babysleepsounds.domain.model.SleepSoundsUiState
import com.example.babysleepsounds.domain.model.SleepTimerOption
import com.example.babysleepsounds.domain.model.SoundPlaybackState

object MockSleepSoundsData {
    val initialState = SleepSoundsUiState(
        sounds = listOf(
            SoundPlaybackState(SleepSound.Rain, isPlaying = true, volume = 0.72f),
            SoundPlaybackState(SleepSound.Ocean, isPlaying = false, volume = 0.58f),
            SoundPlaybackState(SleepSound.Fan, isPlaying = true, volume = 0.42f),
            SoundPlaybackState(SleepSound.WhiteNoise, isPlaying = false, volume = 0.66f),
            SoundPlaybackState(SleepSound.BrownNoise, isPlaying = false, volume = 0.50f),
            SoundPlaybackState(SleepSound.Heartbeat, isPlaying = false, volume = 0.63f)
        ),
        selectedTimerOption = SleepTimerOption.Thirty,
        isTimerPreviewActive = true
    )
}
