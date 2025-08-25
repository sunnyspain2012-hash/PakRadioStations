package com.virk.onlineradio.ui.viewmodel

import android.app.Application
import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.virk.onlineradio.playback.RadioService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(app: Application) : AndroidViewModel(app) {
    private var controller: MediaController? = null

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying
    private val _isBuffering = MutableStateFlow(false)
    val isBuffering: StateFlow<Boolean> = _isBuffering

    init {
        viewModelScope.launch {
            val token = SessionToken(getApplication(), ComponentName(getApplication(), RadioService::class.java))
            controller = MediaController.Builder(getApplication<Context>(), token).buildAsync().await()
            controller?.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) { _isPlaying.value = isPlaying }
                override fun onPlaybackStateChanged(playbackState: Int) {
                    _isBuffering.value = playbackState == Player.STATE_BUFFERING
                }
            })
        }
    }

    fun play(url: String) { controller?.apply { setMediaItem(androidx.media3.common.MediaItem.fromUri(url)); prepare(); play() } }
    fun toggle() { controller?.let { if (it.isPlaying) it.pause() else it.play() } }
}

