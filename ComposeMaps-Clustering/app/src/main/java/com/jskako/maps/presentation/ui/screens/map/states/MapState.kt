package com.jskako.maps.presentation.ui.screens.map.states

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.jskako.maps.domain.model.CustomClusterItem

data class MapState(
    val isMarkerSelected: Boolean = false,
    val defaultCameraPosition: LatLng? = null,
    val selectedClusterItem: CustomClusterItem? = null,
    val map: GoogleMap? = null
)

val DEFAULT_CAMERA_POSITION = LatLng(48.137154, 11.576124)