package com.example.babysleepsounds.domain.model

data class SoundPlaybackState(
    val sound: SleepSound,
    val isPlaying: Boolean = false,
    val volume: Float = 0.65f
)
