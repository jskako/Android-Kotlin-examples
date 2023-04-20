package com.jskako.maps.data.repository

import com.jskako.maps.data.local.BetShopDao
import com.jskako.maps.data.mapper.toBetShopEntity
import com.jskako.maps.data.mapper.toBetShop
import com.jskako.maps.data.mapper.toClusterItem
import com.jskako.maps.data.remote.BetShopsApi
import com.jskako.maps.domain.model.BetShop
import com.jskako.maps.domain.model.CustomClusterItem
import com.jskako.maps.domain.model.Position
import com.jskako.maps.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val dao: BetShopDao,
    private val api: BetShopsApi
) : Repository {

    override suspend fun searchBetShops(
        position: Position
    ): Result<List<BetShop>> {
        return try {
            val searchDto = api.searchBetShops(
                coordinates = position.getCoordinates()
            )
            Result.success(
                searchDto.betshops
                    .map { it.toBetShop() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertBetShops(betShops: List<BetShop>) {
        dao.insertBetShop(betShops.map { it.toBetShopEntity() })
    }

    override fun getBetShops(): Flow<List<CustomClusterItem>> {
        return dao.getAll().map { entities ->
            entities.map { it.toClusterItem() }
        }
    }
}