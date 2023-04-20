package com.jskako.maps.presentation.ui.screens.core.events

import com.jskako.maps.presentation.ui.screens.core.UiText

sealed class UiEvent {
    data class ShowSnackbar(val message: UiText): UiEvent()
}