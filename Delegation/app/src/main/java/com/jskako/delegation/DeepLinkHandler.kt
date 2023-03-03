package com.jskako.delegation

import android.content.Intent
import androidx.activity.ComponentActivity

interface DeepLinkHandler {
    fun handleDeepLink(activity: ComponentActivity, intent: Intent?)
}