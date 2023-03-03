package com.jskako.delegation

import androidx.lifecycle.LifecycleOwner

interface AnalyticsLogger {
    fun registerLifecycleOwner(owner: LifecycleOwner)
}