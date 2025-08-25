package com.virk.onlineradio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.virk.onlineradio.ui.components.AnimatedPlayPause

@Composable
fun CarModeScreen(isBuffering: Boolean, isPlaying: Boolean, onToggle: () -> Unit, onExit: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Car Mode", style = MaterialTheme.typography.headlineMedium)
        AnimatedPlayPause(isBuffering = isBuffering, isPlaying = isPlaying, onToggle = onToggle, modifier = Modifier.size(96.dp))
        Button(onClick = onExit, modifier = Modifier.fillMaxWidth()) { Text("Exit", textAlign = TextAlign.Center) }
    }
}

