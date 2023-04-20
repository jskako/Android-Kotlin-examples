package com.jskako.maps.domain.use_case

data class UseCases(
    val insertBetShop: InsertBetShop,
    val searchBetShop: SearchBetShop,
    val getClusterItems: GetClusterItems,
    val getNetworkStatus: GetNetworkStatus,
    val getDeviceLocation: GetDeviceLocation
)
