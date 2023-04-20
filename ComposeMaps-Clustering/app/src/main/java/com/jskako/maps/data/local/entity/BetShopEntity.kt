package com.jskako.maps.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BetShopEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val longitude: Double,
    val latitude: Double,
    val county: String?,
    val cityId: String?,
    val city: String?,
    val address: String?,
    val phone: String,
    val openingTime: Int,
    val closingTime: Int
)