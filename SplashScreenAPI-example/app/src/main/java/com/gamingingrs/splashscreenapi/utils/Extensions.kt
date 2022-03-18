package com.gamingingrs.splashscreenapi.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun delay(delay: Long = 500L, block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
}

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}