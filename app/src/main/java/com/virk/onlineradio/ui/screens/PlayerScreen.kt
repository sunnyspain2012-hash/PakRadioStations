package com.virk.onlineradio.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.virk.onlineradio.ui.components.AnimatedPlayPause
import com.virk.onlineradio.ui.viewmodel.PlayerViewModel

@Composable
fun PlayerScreen(
    stationName: String,
    favicon: String?,
    streamUrl: String,
    vm: PlayerViewModel
) {
    val isPlaying by vm.isPlaying.collectAsState()
    val isBuffering by vm.isBuffering.collectAsState()

    LaunchedEffect(streamUrl) { vm.play(streamUrl) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(model = favicon, contentDescription = null, modifier = Modifier.size(160.dp))
        Spacer(Modifier.height(16.dp))
        Text(stationName, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(24.dp))
        AnimatedPlayPause(isBuffering = isBuffering, isPlaying = isPlaying, onToggle = { vm.toggle() })
    }
}

