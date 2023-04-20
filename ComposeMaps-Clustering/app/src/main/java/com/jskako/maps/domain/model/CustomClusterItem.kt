package com.jskako.maps.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.jskako.maps.domain.utils.Utils

data class CustomClusterItem(
    private val id: String = "",
    private val title: String = "",
    private val snippet: String = "",
    private val latLng: LatLng = LatLng(0.0, 0.0),
    private val address: String,
    private val phone: String,
    private val openingTime: Int,
    private val closingTime: Int
) : ClusterItem {

    var isSelected: Boolean = false
    override fun getPosition() = latLng

    override fun getTitle() = title

    override fun getSnippet() = snippet

    override fun getZIndex(): Float {
        return 0f
    }

    fun getAddress(): String = address
    fun getPhone(): String = phone
    fun getOpeningTime(): String = formatIntToTimeString(openingTime)
    fun getClosingTime(): String = formatIntToTimeString(closingTime)

    fun isShopOpened(): Boolean {
        val currentTime = Utils().getCurrentTime()
        return currentTime in openingTime..closingTime
    }

    private fun formatIntToTimeString(value: Int): String {
        val stringValue = value.toString()
        return when (stringValue.length) {
            3 -> {
                val formattedValue = "0$stringValue"
                "${formattedValue.substring(0, 2)}:${formattedValue.substring(2, 4)}"
            }

            4 -> {
                "${stringValue.substring(0, 2)}:${stringValue.substring(2, 4)}"
            }

            else -> {
                throw IllegalArgumentException("Input value must be an integer with length 3 or 4.")
            }
        }
    }
}