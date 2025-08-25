package com.virk.onlineradio.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.virk.onlineradio.data.Repository
import com.virk.onlineradio.data.api.StationDto
import com.virk.onlineradio.data.di.NetworkModule
import com.virk.onlineradio.ui.screens.*
import com.virk.onlineradio.ui.viewmodel.PlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNav(modifier: Modifier = Modifier, playerVm: PlayerViewModel) {
    val nav = rememberNavController()
    val repository = remember { Repository(nav.context, NetworkModule.api) }

    Scaffold(topBar = { TopAppBar(title = { Text("Online Radio") }) }) { padding ->
        NavHost(navController = nav, startDestination = "discover", modifier = modifier) {
            composable("discover") {
                DiscoverScreen(repository) { station: StationDto ->
                    val url = station.urlResolved ?: return@DiscoverScreen
                    nav.navigate("player/${station.name}/${url}/${station.favicon ?: ""}")
                }
            }
            composable("favorites") {
                FavoritesScreen(repository.favorites()) { fav ->
                    fav.streamUrl?.let { nav.navigate("player/${fav.name}/${it}/${fav.favicon ?: ""}") }
                }
            }
            composable("recents") {
                RecentsScreen(repository.recents()) { r ->
                    r.streamUrl?.let { nav.navigate("player/${r.name}/${it}/${r.favicon ?: ""}") }
                }
            }
            composable(
                route = "player/{name}/{url}/{icon}",
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("url") { type = NavType.StringType },
                    navArgument("icon") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val url = backStackEntry.arguments?.getString("url") ?: ""
                val icon = backStackEntry.arguments?.getString("icon")
                PlayerScreen(stationName = name, favicon = icon, streamUrl = url, vm = playerVm)
            }
            composable("tool") { ToolScreen() }
        }
    }
}

