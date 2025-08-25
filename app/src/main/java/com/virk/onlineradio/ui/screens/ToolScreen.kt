package com.virk.onlineradio.ui.screens

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.virk.onlineradio.BuildConfig

@Composable
fun ToolScreen() {
    Card(Modifier.fillMaxWidth().padding(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text("App", style = MaterialTheme.typography.titleMedium)
            Text("Package: ${BuildConfig.APPLICATION_ID}")
            Text("Version: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
            Text("Debug: ${BuildConfig.DEBUG}")
            Text("")
            Text("Device", style = MaterialTheme.typography.titleMedium)
            Text("Manufacturer: ${Build.MANUFACTURER}")
            Text("Model: ${Build.MODEL}")
            Text("SDK: ${Build.VERSION.SDK_INT}")
        }
    }
}

