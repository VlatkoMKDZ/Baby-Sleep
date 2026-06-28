package com.example.babysleepsounds.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.domain.model.SoundPlaybackState
import com.example.babysleepsounds.ui.theme.*

@Composable
fun SoundCard(state: SoundPlaybackState, onToggle: () -> Unit, onVolumeChange: (Float) -> Unit, modifier: Modifier = Modifier) {
    val elevation by animateDpAsState(if (state.isPlaying) 12.dp else 4.dp, label = "soundCardElevation")
    Card(
        modifier = modifier.border(BorderStroke(if (state.isPlaying) 1.5.dp else 1.dp, if (state.isPlaying) SoftLavender else Color.White.copy(.08f)), RoundedCornerShape(30.dp)),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Column(
            Modifier.background(Brush.linearGradient(listOf(CardMidnight, Color(0xFF252B55), SoftLavender.copy(alpha = if (state.isPlaying) .20f else .06f)))).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(72.dp).clip(RoundedCornerShape(24.dp)).background(Color.White.copy(.08f)), contentAlignment = Alignment.Center) {
                    SoundIllustration(state.sound)
                }
                Spacer(Modifier.weight(1f))
                FilledIconButton(onClick = onToggle, colors = IconButtonDefaults.filledIconButtonColors(containerColor = if (state.isPlaying) SoftLavender else Color.White.copy(.12f))) {
                    Icon(if (state.isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow, contentDescription = if (state.isPlaying) "Pause ${state.sound.displayName}" else "Play ${state.sound.displayName}")
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(state.sound.displayName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                AnimatedVisibility(state.isPlaying) { EqualizerBars() }
            }
            Text("Volume ${(state.volume * 100).toInt()}%", style = MaterialTheme.typography.labelMedium, color = TextLightGray)
            Slider(value = state.volume, onValueChange = onVolumeChange, colors = SliderDefaults.colors(thumbColor = CloudWhite, activeTrackColor = LightPurple, inactiveTrackColor = Color.White.copy(.16f)))
        }
    }
}

@Composable
private fun EqualizerBars() {
    Row(horizontalArrangement = Arrangement.spacedBy(3.dp), verticalAlignment = Alignment.Bottom) {
        listOf(10.dp, 18.dp, 13.dp).forEach { height ->
            Box(Modifier.width(4.dp).height(height).clip(RoundedCornerShape(99.dp)).background(SoftYellowMoon))
        }
    }
}
