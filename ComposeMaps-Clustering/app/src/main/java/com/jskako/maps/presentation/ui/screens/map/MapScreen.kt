package com.jskako.maps.presentation.ui.screens.map

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.jskako.maps.domain.model.CustomClusterItem
import com.jskako.maps.presentation.ui.composable.MapScaffold
import com.jskako.maps.presentation.ui.composable.MarkerInfoSection
import com.jskako.maps.presentation.ui.screens.core.events.ClusterCommandEvent
import com.jskako.maps.presentation.ui.screens.map.clusters.CustomClusterRenderer
import com.jskako.maps.presentation.ui.screens.map.events.MapEvent
import com.jskako.maps.presentation.ui.screens.core.events.SearchEvent
import com.jskako.maps.presentation.ui.screens.core.events.UiEvent
import com.jskako.maps.presentation.ui.screens.map.states.DEFAULT_CAMERA_POSITION
import com.jskako.maps.presentation.ui.utils.executeCommands
import com.jskako.maps.presentation.ui.utils.navigateToLocationOnGoogleMaps

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: MapViewModel = hiltViewModel()
) {
    val clusterItems by viewModel.clusterItems.collectAsState(initial = emptyList())
    val context = LocalContext.current
    lateinit var clusterManager: ClusterManager<CustomClusterItem>
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            viewModel.mapState.defaultCameraPosition ?: DEFAULT_CAMERA_POSITION, 9f
        )
    }

    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                viewModel.getDeviceLocation(LocationServices.getFusedLocationProviderClient(context))
            }
        }
    )

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }

    LaunchedEffect(viewModel.mapState.defaultCameraPosition) {
        viewModel.mapState.defaultCameraPosition.let {
            if (it != null) {
                viewModel.mapState.defaultCameraPosition.run {
                    viewModel.mapState.map.let { map ->
                        if (map != null) {
                            viewModel.moveCameraOnPosition(
                                LatLng(
                                    this?.latitude ?: DEFAULT_CAMERA_POSITION.latitude,
                                    this?.longitude ?: DEFAULT_CAMERA_POSITION.longitude
                                ),
                                map
                            )
                            viewModel.onMapEvent(MapEvent.SetDefaultCameraPosition(null))
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            if (!viewModel.mapState.isMarkerSelected) {
                MapScaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 12.dp),
                    enabled = viewModel.searchState.searchEnabled,
                    onSearchClick = {
                        viewModel.onSearchEvent(SearchEvent.OnSearch)
                    },
                    onLocationClick = {
                        locationPermissionResultLauncher.launch(
                            ACCESS_FINE_LOCATION
                        )
                    }
                )
            }
        },
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false
            ),
        ) {
            MapEffect(key1 = clusterItems) { map ->

                clusterManager = initializeClusterManager(context, map)
                setMapListeners(map, viewModel, clusterManager, clusterItems)

                clusterManager.run {
                    setOnClusterItemClickListener { clusterItem ->
                        val clusterRender = renderer as CustomClusterRenderer
                        val marker = clusterRender.getMarker(clusterItem)

                        viewModel.moveCameraOnMarker(marker, map)
                        viewModel.onMapEvent(MapEvent.SetMarkerSelected(true))

                        executeCommands(
                            listOf(ClusterCommandEvent.AddItem(clusterItem))
                        )

                        viewModel.changeMarkerIcon(clusterItem, clusterRender, true)

                        true
                    }
                    executeCommands(
                        listOf(ClusterCommandEvent.AddItems(clusterItems))
                    )
                }
            }
            LaunchedEffect(key1 = cameraPositionState.isMoving) {
                if (!cameraPositionState.isMoving) {
                    clusterManager.onCameraIdle()
                    clusterManager.cluster()
                }
            }
        }
    }

    viewModel.mapState.run {
        MarkerInfoSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .padding(start = 8.dp, end = 8.dp)
                .background(Color.White),
            visible = isMarkerSelected,
            markersInfo = viewModel.buildMarkerInfo(selectedClusterItem),
            onNavigate = {
                context.navigateToLocationOnGoogleMaps(
                    selectedClusterItem?.position?.latitude ?: 00.00,
                    selectedClusterItem?.position?.longitude ?: 00.00
                )
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewModel.searchState.showProgressBar -> CircularProgressIndicator()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.primary),
        contentAlignment = Alignment.BottomCenter
    ) {
        when {
            !viewModel.networkState.networkEnabled -> {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Network not available",
                    color = Color.White
                )
            }
        }
    }
}

@SuppressLint("PotentialBehaviorOverride")
private fun setMapListeners(
    map: GoogleMap,
    viewModel: MapViewModel,
    clusterManager: ClusterManager<CustomClusterItem>,
    cachedBetShops: List<CustomClusterItem>
) {

    map.run {
        viewModel.onMapEvent(MapEvent.SetMap(this))
        clusterManager.run {
            setOnMapClickListener {
                viewModel.run {
                    if (mapState.isMarkerSelected) {
                        changeMarkerIcon(
                            mapState.selectedClusterItem,
                            renderer as CustomClusterRenderer,
                            false
                        )
                        run {
                            executeCommands(
                                listOf(ClusterCommandEvent.AddItems(cachedBetShops))
                            )
                        }
                        onMapEvent(MapEvent.SetMarkerSelected(false))
                    }
                }
            }

            setOnCameraMoveStartedListener {
                viewModel.onSearchEvent(SearchEvent.SearchEnabled(false))
                cluster()
            }

            setOnCameraIdleListener {
                projection.visibleRegion.run {
                    viewModel.setPositionState(
                        farRight.latitude,
                        farRight.longitude,
                        nearLeft.latitude,
                        nearLeft.longitude
                    )
                }
                cluster()
                viewModel.onSearchEvent(SearchEvent.SearchEnabled(true))
            }

            setOnMarkerClickListener(clusterManager)
        }
    }
}

private fun initializeClusterManager(
    context: Context,
    map: GoogleMap
): ClusterManager<CustomClusterItem> {
    return ClusterManager<CustomClusterItem>(context, map).apply {
        renderer = CustomClusterRenderer(context, map, this)
    }
}