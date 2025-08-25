package com.virk.onlineradio.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SleepTimer(private val scope: CoroutineScope, private val onTimeout: () -> Unit) {
    private var job: Job? = null
    fun start(minutes: Int) {
        cancel()
        job = scope.launch { delay(minutes * 60_000L); onTimeout() }
    }
    fun cancel() { job?.cancel(); job = null }
}

