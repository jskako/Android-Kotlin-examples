package com.jskako.delegation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class AnalyticsLoggerImpl : AnalyticsLogger, LifecycleEventObserver {

    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> println("User opened the screen")
            Lifecycle.Event.ON_PAUSE -> println("User leaves the screen")
            else -> Unit
        }
    }
}