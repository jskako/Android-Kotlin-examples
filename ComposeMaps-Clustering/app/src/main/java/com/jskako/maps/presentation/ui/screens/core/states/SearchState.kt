package com.jskako.maps.presentation.ui.screens.core.states

import com.jskako.maps.domain.model.BetShop

data class SearchState(
    val searchEnabled: Boolean = false,
    val betShops: List<BetShop> = emptyList(),
    val showProgressBar: Boolean = false,
)