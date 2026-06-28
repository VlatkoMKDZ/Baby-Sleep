package com.example.babysleepsounds.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.domain.model.SleepTimerOption
import com.example.babysleepsounds.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerSection(selectedTimerOption: SleepTimerOption?, isTimerPreviewActive: Boolean, onSelectTimer: (SleepTimerOption) -> Unit, onClearTimerSelection: () -> Unit, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(32.dp), colors = CardDefaults.cardColors(containerColor = Color.Transparent), border = BorderStroke(1.dp, Color.White.copy(.10f))) {
        Column(Modifier.background(Brush.linearGradient(listOf(Color(0xFF242B58), CardMidnight))).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TimerIllustration()
                Column(Modifier.weight(1f)) {
                    Text("Sleep Timer", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
                    Text("Sounds will gently stop after the timer ends.", style = MaterialTheme.typography.bodyMedium, color = TextLightGray)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                SleepTimerOption.entries.forEach { option ->
                    FilterChip(
                        selected = selectedTimerOption == option,
                        onClick = { onSelectTimer(option) },
                        label = { Text(option.label.replace("m", " min")) },
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = SoftLavender, containerColor = Color.Transparent, selectedLabelColor = TextWhite, labelColor = TextLightGray),
                        border = FilterChipDefaults.filterChipBorder(enabled = true, selected = selectedTimerOption == option, borderColor = LightPurple.copy(.45f), selectedBorderColor = SoftLavender)
                    )
                }
            }
            Button(onClick = { onSelectTimer(selectedTimerOption ?: SleepTimerOption.Thirty) }, modifier = Modifier.fillMaxWidth().height(52.dp), shape = RoundedCornerShape(20.dp), colors = ButtonDefaults.buttonColors(containerColor = SoftLavender)) { Text("Start Timer") }
            if (isTimerPreviewActive) OutlinedButton(onClick = onClearTimerSelection, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = TextLightGray)) { Text("Clear Timer") }
        }
    }
}
