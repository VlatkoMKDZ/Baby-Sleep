package com.example.babysleepsounds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.babysleepsounds.ui.screens.SleepSoundsScreen
import com.example.babysleepsounds.ui.theme.BabySleepSoundsTheme
import com.example.babysleepsounds.viewmodel.SleepSoundsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: SleepSoundsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BabySleepSoundsTheme {
                val uiState by viewModel.uiState.collectAsState()
                SleepSoundsScreen(
                    uiState = uiState,
                    onToggleSound = viewModel::toggle,
                    onVolumeChange = viewModel::setVolume,
                    onSelectTimer = viewModel::selectTimer,
                    onClearTimerSelection = viewModel::clearTimerSelection
                )
            }
        }
    }
}
