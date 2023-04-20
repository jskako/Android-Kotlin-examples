package com.jskako.maps.presentation.ui.screens.map

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.jskako.maps.domain.connectivity.ConnectivityObserver
import com.jskako.maps.domain.model.CustomClusterItem
import com.jskako.maps.domain.model.Position
import com.jskako.maps.domain.use_case.UseCases
import com.jskako.maps.presentation.ui.screens.map.events.MapEvent
import com.jskako.maps.presentation.ui.screens.core.events.SearchEvent
import com.jskako.maps.presentation.ui.screens.map.states.MapState
import com.jskako.maps.presentation.ui.screens.core.states.SearchState
import com.jskako.maps.presentation.ui.screens.core.events.UiEvent
import com.jskako.maps.presentation.ui.screens.core.UiText
import com.jskako.maps.presentation.ui.screens.core.states.NetworkState
import com.jskako.maps.presentation.ui.screens.map.clusters.CustomClusterRenderer
import com.jskako.maps.presentation.ui.screens.map.states.MarkerInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var searchState by mutableStateOf(SearchState())
        private set
    var mapState by mutableStateOf(MapState())
        private set

    private val networkObserver = useCases.getNetworkStatus()
    var networkState by mutableStateOf(NetworkState())
        private set

    private var positionState by mutableStateOf(Position())
    val clusterItems = useCases.getClusterItems()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        observeNetworkState()
    }

    private fun observeNetworkState() {
        viewModelScope.launch {
            networkObserver.collectLatest {
                networkState = when (it) {
                    ConnectivityObserver.Status.Available -> {
                        networkState.copy(networkEnabled = true)
                    }

                    else -> {
                        networkState.copy(networkEnabled = false)
                    }
                }
            }
        }
    }

    fun moveCameraOnPosition(position: LatLng, map: GoogleMap) {
        val cameraPosition = CameraPosition.Builder()
            .target(position)
            .zoom(map.cameraPosition.zoom)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun moveCameraOnMarker(marker: Marker, map: GoogleMap) {
        marker.let { clickedMarker ->
            val cameraPosition = CameraPosition.Builder()
                .target(clickedMarker.position)
                .zoom(map.cameraPosition.zoom)
                .build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    fun changeMarkerIcon(
        clusterItem: CustomClusterItem?,
        clusterRender: CustomClusterRenderer,
        isSelected: Boolean
    ) {
        if (clusterItem != null) {
            clusterItem.isSelected = isSelected
            clusterRender.onItemSelected(clusterItem, clusterRender.getMarker(clusterItem))

            if (isSelected) {
                onMapEvent(MapEvent.SetSelectedClusterItem(clusterItem))
            } else {
                onMapEvent(MapEvent.SetSelectedClusterItem(null))
            }
        }
    }

    fun onSearchEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearch -> {
                viewModelScope.launch {
                    if (networkState.networkEnabled) {
                        executeSearch()
                    } else {
                        showSnackBar("Error - Network not available.")
                    }
                }
            }

            is SearchEvent.SearchEnabled -> {
                searchState = searchState.copy(searchEnabled = event.searchEnabled)
            }
        }
    }

    fun onMapEvent(event: MapEvent) {
        mapState = when (event) {
            is MapEvent.SetDefaultCameraPosition -> {
                mapState.copy(defaultCameraPosition = event.defaultCameraPosition)
            }

            is MapEvent.SetMarkerSelected -> {
                mapState.copy(isMarkerSelected = event.isMarkerSelected)
            }

            is MapEvent.SetSelectedClusterItem -> {
                mapState.copy(selectedClusterItem = event.selectedClusterItem)
            }
            is MapEvent.SetMap -> {
                mapState.copy(map = event.map)
            }
        }
    }

    fun setPositionState(
        topRightLatitude: Double,
        topRightLongitude: Double,
        bottomLeftLatitude: Double,
        bottomLeftLongitude: Double
    ) {
        positionState = positionState.copy(
            topRightLatitude = topRightLatitude,
            topRightLongitude = topRightLongitude,
            bottomLeftLatitude = bottomLeftLatitude,
            bottomLeftLongitude = bottomLeftLongitude
        )
    }

    fun buildMarkerInfo(selectedClusterItem: CustomClusterItem?): List<MarkerInfo> {
        return listOf(
            MarkerInfo(selectedClusterItem?.getAddress() ?: "", Icons.Default.Place),
            MarkerInfo(selectedClusterItem?.getPhone() ?: "", Icons.Default.Phone),
            MarkerInfo(
                buildMarkerTimeInfo(selectedClusterItem), Icons.Default.DateRange
            )
        )
    }

    private fun buildMarkerTimeInfo(selectedClusterItem: CustomClusterItem?): String {
        return if (selectedClusterItem?.isShopOpened() == true) {
            "Works until ${selectedClusterItem.getClosingTime()}"
        } else {
            "Opening at ${selectedClusterItem?.getOpeningTime()}"
        }
    }

    private suspend fun showSnackBar(message: String) {
        _uiEvent.send(
            UiEvent.ShowSnackbar(
                UiText.DynamicString(message)
            )
        )
    }

    fun getDeviceLocation(fusedLocationProviderClient: FusedLocationProviderClient) {
        viewModelScope.launch {
            val deviceLocation = useCases.getDeviceLocation(fusedLocationProviderClient)
            mapState = mapState.copy(
                defaultCameraPosition = LatLng(
                    deviceLocation.latitude,
                    deviceLocation.longitude
                )
            )
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            searchState = searchState.copy(
                searchEnabled = false,
                betShops = emptyList(),
                showProgressBar = true
            )
            useCases
                .searchBetShop(positionState)
                .onSuccess { betShops ->
                    useCases.insertBetShop(betShops)
                    searchState = searchState.copy(
                        betShops = betShops,
                        searchEnabled = true,
                        showProgressBar = false
                    )
                }
                .onFailure {
                    searchState = searchState.copy(searchEnabled = true, showProgressBar = false)
                    showSnackBar("Something went wrong")
                }
        }
    }
}