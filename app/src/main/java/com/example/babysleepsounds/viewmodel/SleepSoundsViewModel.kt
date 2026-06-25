package com.example.babysleepsounds.viewmodel

import androidx.lifecycle.ViewModel
import com.example.babysleepsounds.data.mock.MockSleepSoundsData
import com.example.babysleepsounds.domain.model.SleepSound
import com.example.babysleepsounds.domain.model.SleepSoundsUiState
import com.example.babysleepsounds.domain.model.SleepTimerOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SleepSoundsViewModel : ViewModel() {
    private val mutableUiState = MutableStateFlow(MockSleepSoundsData.initialState)
    val uiState: StateFlow<SleepSoundsUiState> = mutableUiState.asStateFlow()

    fun toggle(sound: SleepSound) {
        mutableUiState.update { state ->
            state.copy(
                sounds = state.sounds.map { soundState ->
                    if (soundState.sound == sound) soundState.copy(isPlaying = !soundState.isPlaying) else soundState
                }
            )
        }
    }

    fun setVolume(sound: SleepSound, volume: Float) {
        mutableUiState.update { state ->
            state.copy(
                sounds = state.sounds.map { soundState ->
                    if (soundState.sound == sound) soundState.copy(volume = volume.coerceIn(0f, 1f)) else soundState
                }
            )
        }
    }

    fun selectTimer(option: SleepTimerOption) {
        mutableUiState.update { state ->
            state.copy(selectedTimerOption = option, isTimerPreviewActive = true)
        }
    }

    fun clearTimerSelection() {
        mutableUiState.update { state ->
            state.copy(selectedTimerOption = null, isTimerPreviewActive = false)
        }
    }
}
