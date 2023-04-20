package com.jskako.maps.presentation.ui.screens.core.events

sealed class SearchEvent {
    object OnSearch : SearchEvent()
    data class SearchEnabled(val searchEnabled: Boolean): SearchEvent()
}