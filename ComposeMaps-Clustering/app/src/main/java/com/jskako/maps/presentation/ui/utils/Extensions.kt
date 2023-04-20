package com.jskako.maps.presentation.ui.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.view.WindowCompat
import com.google.maps.android.clustering.ClusterManager
import com.jskako.maps.domain.model.CustomClusterItem
import com.jskako.maps.presentation.ui.screens.core.events.ClusterCommandEvent

internal fun Activity.setAppearanceLightStatusBar(isSystemInDarkTheme: Boolean) {
    WindowCompat.getInsetsController(this.window, this.window.decorView).apply {
        isAppearanceLightStatusBars = isSystemInDarkTheme
    }
}

internal fun ClusterManager<CustomClusterItem>.executeCommands(commands: List<ClusterCommandEvent>) {
    commands.forEach { clusterCommandEvent ->
        when (clusterCommandEvent) {
            is ClusterCommandEvent.AddItem -> {
                clearItems()
                addItem(clusterCommandEvent.clusterItem)
                cluster()
            }

            is ClusterCommandEvent.AddItems -> {
                clearItems()
                addItems(clusterCommandEvent.clusterItems)
                cluster()
            }

            ClusterCommandEvent.ClearItems -> clearItems()
            ClusterCommandEvent.Cluster -> cluster()
        }
    }
}

fun Context.navigateToLocationOnGoogleMaps(latitude: Double, longitude: Double) {
    val gmmIntentUri = Uri.parse("google.navigation:q=$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    } else {
        // Google Maps is not installed on the device, hande this case
    }
}



