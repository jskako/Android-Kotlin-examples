package com.jskako.maps.data.remote.dto

import com.squareup.moshi.Json

data class BetShopDto(
    val name: String?,
    val location: LocationDto,
    val id: Int,
    val county: String?,
    @field:Json(name = "city_id")
    val cityId: String?,
    val city: String?,
    val address: String?,
)