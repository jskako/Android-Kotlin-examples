package com.jskako.maps.data.mapper

import com.google.android.gms.maps.model.LatLng
import com.jskako.maps.data.generateMockedPhoneNumber
import com.jskako.maps.data.local.entity.BetShopEntity
import com.jskako.maps.data.remote.dto.BetShopDto
import com.jskako.maps.domain.model.BetShop
import com.jskako.maps.domain.model.CustomClusterItem

fun BetShopEntity.toClusterItem(): CustomClusterItem {
    return CustomClusterItem(
        id = id.toString(),
        title = name ?: "",
        snippet = "",
        latLng = LatLng(latitude, longitude),
        address = "$city\n$address\n",
        phone = phone,
        openingTime = openingTime,
        closingTime = closingTime
    )
}

fun BetShop.toBetShopEntity(): BetShopEntity {
    return BetShopEntity(
        id = id,
        name = name,
        longitude = longitude,
        latitude = latitude,
        county = county,
        cityId = cityId,
        city = city,
        phone = phone,
        address = address,
        openingTime = openingTime,
        closingTime = closingTime
    )
}

fun BetShopDto.toBetShop(): BetShop {
    return BetShop(
        id = id,
        name = name,
        longitude = location.lng,
        latitude = location.lat,
        county = county,
        cityId = cityId,
        city = city,
        address = address,
        phone = mockedPhoneNumber,
        openingTime = MOCKED_OPENING_TIME,
        closingTime = MOCKED_CLOSING_TIME
    )
}

private var mockedPhoneNumber = generateMockedPhoneNumber()
private const val MOCKED_OPENING_TIME = 800
private const val MOCKED_CLOSING_TIME = 1600