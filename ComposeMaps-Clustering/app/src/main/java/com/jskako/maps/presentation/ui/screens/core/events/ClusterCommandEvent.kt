package com.jskako.maps.presentation.ui.screens.core.events

import com.jskako.maps.domain.model.CustomClusterItem

sealed class ClusterCommandEvent {
    object ClearItems : ClusterCommandEvent()
    object Cluster : ClusterCommandEvent()
    data class AddItem(val clusterItem: CustomClusterItem): ClusterCommandEvent()
    data class AddItems(val clusterItems: List<CustomClusterItem>): ClusterCommandEvent()
}