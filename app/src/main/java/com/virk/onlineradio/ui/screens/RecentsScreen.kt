package com.virk.onlineradio.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.virk.onlineradio.data.db.RecentStation
import kotlinx.coroutines.flow.Flow

@Composable
fun RecentsScreen(recentsFlow: Flow<List<RecentStation>>, onClick: (RecentStation) -> Unit) {
    val recents by recentsFlow.collectAsState(initial = emptyList())
    LazyColumn(Modifier.fillMaxSize().padding(12.dp)) {
        items(recents) { r ->
            Card(Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onClick(r) }) {
                Row(Modifier.padding(12.dp)) {
                    AsyncImage(model = r.favicon, contentDescription = null, modifier = Modifier.size(40.dp))
                    Spacer(Modifier.width(12.dp))
                    Column { Text(r.name); Text(r.streamUrl ?: "") }
                }
            }
        }
    }
}

