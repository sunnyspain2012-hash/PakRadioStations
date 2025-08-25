package com.virk.onlineradio.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.virk.onlineradio.data.Repository
import com.virk.onlineradio.data.api.StationDto
import kotlinx.coroutines.launch

@Composable
fun DiscoverScreen(repository: Repository, onPlay: (StationDto) -> Unit) {
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<StationDto>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Search stations") })
        Spacer(Modifier.height(8.dp))
        Row { 
            androidx.compose.material3.Button(onClick = {
                scope.launch {
                    try { results = repository.search(name = query, country = null, language = null) ; error = null }
                    catch (t: Throwable) { error = t.message }
                }
            }) { Text("Search") }
        }
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(results) { station ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onPlay(station) }) {
                    Row(Modifier.padding(12.dp)) {
                        AsyncImage(model = station.favicon, contentDescription = null, modifier = Modifier.size(40.dp))
                        Spacer(Modifier.width(12.dp))
                        Column { Text(station.name, style = MaterialTheme.typography.titleMedium); Text(station.country ?: "", style = MaterialTheme.typography.bodySmall) }
                    }
                }
            }
        }
    }
}

