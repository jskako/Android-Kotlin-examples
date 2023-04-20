package com.jskako.maps.presentation.ui.screens.core.events

sealed class NetworkEvent {
    data class SetNetworkEnabled(val networkEnabled: Boolean): NetworkEvent()
}