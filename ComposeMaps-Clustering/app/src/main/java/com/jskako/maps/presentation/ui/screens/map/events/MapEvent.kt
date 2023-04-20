package com.jskako.maps.presentation.ui.screens.map.events

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.jskako.maps.domain.model.CustomClusterItem

sealed class MapEvent {
    data class SetMarkerSelected(val isMarkerSelected: Boolean): MapEvent()
    data class SetDefaultCameraPosition(val defaultCameraPosition: LatLng?): MapEvent()
    data class SetSelectedClusterItem(val selectedClusterItem: CustomClusterItem?): MapEvent()
    data class SetMap(val map: GoogleMap?): MapEvent()
}