package com.example.babysleepsounds.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.domain.model.SleepSound
import com.example.babysleepsounds.domain.model.SleepSoundsUiState
import com.example.babysleepsounds.domain.model.SleepTimerOption
import com.example.babysleepsounds.ui.components.SoundCard
import com.example.babysleepsounds.ui.components.TimerSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepSoundsScreen(
    uiState: SleepSoundsUiState,
    onToggleSound: (SleepSound) -> Unit,
    onVolumeChange: (SleepSound, Float) -> Unit,
    onSelectTimer: (SleepTimerOption) -> Unit,
    onClearTimerSelection: () -> Unit
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Baby Sleep Sounds", fontWeight = FontWeight.SemiBold) }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
            Text("Compose-first mock screens for a calming bedtime sound mixer.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.sounds, key = { it.sound.name }) { soundState ->
                    SoundCard(
                        state = soundState,
                        onToggle = { onToggleSound(soundState.sound) },
                        onVolumeChange = { onVolumeChange(soundState.sound, it) }
                    )
                }
            }
            TimerSection(
                selectedTimerOption = uiState.selectedTimerOption,
                isTimerPreviewActive = uiState.isTimerPreviewActive,
                onSelectTimer = onSelectTimer,
                onClearTimerSelection = onClearTimerSelection
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SleepSoundsScreenPreview() {
    com.example.babysleepsounds.ui.theme.BabySleepSoundsTheme {
        SleepSoundsScreen(
            uiState = com.example.babysleepsounds.data.mock.MockSleepSoundsData.initialState,
            onToggleSound = {},
            onVolumeChange = { _, _ -> },
            onSelectTimer = {},
            onClearTimerSelection = {}
        )
    }
}
