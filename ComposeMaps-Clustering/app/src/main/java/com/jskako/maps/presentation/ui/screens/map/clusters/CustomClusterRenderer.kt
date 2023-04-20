package com.jskako.maps.presentation.ui.screens.map.clusters

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.jskako.maps.R
import com.jskako.maps.domain.model.CustomClusterItem

class CustomClusterRenderer(
    context: Context, map: GoogleMap, clusterManager: ClusterManager<CustomClusterItem>
) : DefaultClusterRenderer<CustomClusterItem>(context, map, clusterManager) {

    override fun shouldRenderAsCluster(cluster: Cluster<CustomClusterItem>): Boolean =
        cluster.size >= 2

    override fun onBeforeClusterItemRendered(
        item: CustomClusterItem, markerOptions: MarkerOptions
    ) {
        markerOptions.icon(isItemSelected(item))
    }

    override fun onClusterItemUpdated(item: CustomClusterItem, marker: Marker) {
        super.onClusterItemUpdated(item, marker)
        marker.setIcon(isItemSelected(item))
    }

    private fun getBitmapDescriptor(id: Int): BitmapDescriptor {
        return BitmapDescriptorFactory.fromResource(id)
    }

    fun onItemSelected(item: CustomClusterItem, marker: Marker) {
        marker.setIcon(isItemSelected(item))
    }

    private fun isItemSelected(item: CustomClusterItem): BitmapDescriptor {
        return if (item.isSelected) {
            getBitmapDescriptor(SHOP_ICON_CLICKED)
        } else {
            getBitmapDescriptor(SHOP_ICON)
        }
    }
}

@SuppressLint("NonConstantResourceId")
private val SHOP_ICON = R.drawable.ic_pin_normal

@SuppressLint("NonConstantResourceId")
private val SHOP_ICON_CLICKED = R.drawable.ic_pin_active