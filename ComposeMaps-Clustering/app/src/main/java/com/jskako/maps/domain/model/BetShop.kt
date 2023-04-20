package com.jskako.maps.domain.model

data class BetShop(
    val id: Int,
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

/**
 *  For production we can add validations in init of data class
 *  Example: require(phone.isValidNumber) { "Phone number is invalid" }
 *  Also proper error can be return instead of string
 */
