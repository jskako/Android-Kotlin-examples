package com.gamingingrs.splashscreenapi.utils

import android.os.Handler
import android.os.Looper

fun delay(delay: Long = 500L, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
}