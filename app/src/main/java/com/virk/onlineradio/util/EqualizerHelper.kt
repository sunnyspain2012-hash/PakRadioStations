package com.virk.onlineradio.util

import android.content.Context
import android.media.audiofx.Equalizer

class EqualizerHelper(audioSessionIdProvider: () -> Int?) {
    private var equalizer: Equalizer? = null

    fun attach() {
        val sessionId = audioSessionIdProvider() ?: return
        if (equalizer?.enabled == true && equalizer?.audioSessionId == sessionId) return
        release()
        equalizer = Equalizer(0, sessionId).apply { enabled = true }
    }

    fun setBandLevel(band: Short, level: Short) { equalizer?.setBandLevel(band, level) }
    fun getNumberOfBands(): Short = equalizer?.numberOfBands ?: 0
    fun getBandLevelRange(): ShortArray = equalizer?.bandLevelRange ?: shortArrayOf(0, 0)
    fun getCenterFreq(band: Short): Int = equalizer?.getCenterFreq(band) ?: 0
    fun release() { equalizer?.release(); equalizer = null }
}

