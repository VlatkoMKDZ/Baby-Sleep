package com.example.babysleepsounds.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.babysleepsounds.domain.model.SleepTimerOption

@Composable
fun TimerSection(
    selectedTimerOption: SleepTimerOption?,
    isTimerPreviewActive: Boolean,
    onSelectTimer: (SleepTimerOption) -> Unit,
    onClearTimerSelection: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Sleep Timer", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(
                text = selectedTimerOption?.let { "Mock timer selected: ${it.minutes} minutes" }
                    ?: "Choose when all sounds will gently fade out in the future audio build.",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                SleepTimerOption.entries.forEach { option ->
                    FilterChip(
                        selected = selectedTimerOption == option,
                        onClick = { onSelectTimer(option) },
                        label = { Text(option.label) }
                    )
                }
            }
            if (isTimerPreviewActive) {
                OutlinedButton(onClick = onClearTimerSelection, modifier = Modifier.fillMaxWidth()) { Text("Clear mock timer") }
            } else {
                Button(onClick = { onSelectTimer(SleepTimerOption.Thirty) }, modifier = Modifier.fillMaxWidth()) { Text("Preview 30 minute timer") }
            }
        }
    }
}
