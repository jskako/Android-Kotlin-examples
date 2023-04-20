package com.jskako.maps.domain.repository

import com.jskako.maps.domain.model.BetShop
import com.jskako.maps.domain.model.CustomClusterItem
import com.jskako.maps.domain.model.Position
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun searchBetShops(
        position: Position
    ): Result<List<BetShop>>

    suspend fun insertBetShops(betShops: List<BetShop>)

    fun getBetShops(): Flow<List<CustomClusterItem>>
}