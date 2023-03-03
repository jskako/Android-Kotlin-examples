package com.jskako.delegation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.jskako.DeepLinkHandlerImpl

class MainActivity : ComponentActivity(), AnalyticsLogger by AnalyticsLoggerImpl(),
    DeepLinkHandler by DeepLinkHandlerImpl() {

    private val obj by MyLazy {
        println("Hello World")
        3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLifecycleOwner(this)

        println(obj)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeepLink(this, intent)
    }
}