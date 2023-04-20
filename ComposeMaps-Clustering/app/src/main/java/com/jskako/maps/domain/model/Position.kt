package com.jskako.maps.domain.model

data class Position(
    val topRightLatitude: Double = MOCKED_TOP_RIGHT_LATITUDE,
    val topRightLongitude: Double = MOCKED_TOP_RIGHT_LONGITUDE,
    val bottomLeftLatitude: Double = MOCKED_BOTTOM_LEFT_LATITUDE,
    val bottomLeftLongitude: Double = MOCKED_BOTTOM_LEFT_LONGITUDE
) {
    fun isValid(): Boolean {
        return topRightLatitude.isNaN() || topRightLongitude.isNaN() || bottomLeftLatitude.isNaN() || bottomLeftLongitude.isNaN()
    }

    fun getCoordinates(): String {
        return "$topRightLatitude,$topRightLongitude,$bottomLeftLatitude,$bottomLeftLongitude"
    }
}

private const val MOCKED_TOP_RIGHT_LATITUDE = 48.16124
private const val MOCKED_TOP_RIGHT_LONGITUDE = 11.60912
private const val MOCKED_BOTTOM_LEFT_LATITUDE = 48.12229
private const val MOCKED_BOTTOM_LEFT_LONGITUDE = 11.52741
