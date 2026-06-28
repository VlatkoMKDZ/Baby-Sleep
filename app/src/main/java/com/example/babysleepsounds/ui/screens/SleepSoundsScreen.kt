package com.example.babysleepsounds.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.domain.model.SleepSound
import com.example.babysleepsounds.domain.model.SleepSoundsUiState
import com.example.babysleepsounds.domain.model.SleepTimerOption
import com.example.babysleepsounds.ui.components.*
import com.example.babysleepsounds.ui.theme.TextLightGray

@Composable
fun SleepSoundsScreen(uiState: SleepSoundsUiState, onToggleSound: (SleepSound) -> Unit, onVolumeChange: (SleepSound, Float) -> Unit, onSelectTimer: (SleepTimerOption) -> Unit, onClearTimerSelection: () -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    Box(Modifier.fillMaxSize()) {
        BedtimeBackground(Modifier.matchParentSize())
        Scaffold(containerColor = Color.Transparent, bottomBar = { BedtimeBottomNavigation(selectedTab, { selectedTab = it }) }) { padding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) { HeroHeader() }
                items(uiState.sounds, key = { it.sound.name }) { soundState ->
                    SoundCard(state = soundState, onToggle = { onToggleSound(soundState.sound) }, onVolumeChange = { onVolumeChange(soundState.sound, it) }, modifier = Modifier.fillMaxWidth())
                }
                item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                    TimerSection(selectedTimerOption = uiState.selectedTimerOption, isTimerPreviewActive = uiState.isTimerPreviewActive, onSelectTimer = onSelectTimer, onClearTimerSelection = onClearTimerSelection, modifier = Modifier.padding(top = 4.dp, bottom = 10.dp))
                }
            }
        }
    }
}

@Composable
private fun HeroHeader() {
    Row(Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Baby Sleep Sounds", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold)
            Text("Mix soothing sounds to help your little one sleep peacefully.", style = MaterialTheme.typography.bodyLarge, color = TextLightGray)
        }
        HeroBabyMoon(Modifier.size(112.dp))
        IconButton(onClick = { }) { Icon(Icons.Rounded.Settings, contentDescription = "Settings", tint = TextLightGray) }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SleepSoundsScreenPreview() {
    com.example.babysleepsounds.ui.theme.BabySleepSoundsTheme {
        SleepSoundsScreen(com.example.babysleepsounds.data.mock.MockSleepSoundsData.initialState, {}, { _, _ -> }, {}, {})
    }
}
