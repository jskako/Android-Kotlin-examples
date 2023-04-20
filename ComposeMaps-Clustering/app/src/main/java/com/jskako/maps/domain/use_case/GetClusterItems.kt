package com.jskako.maps.domain.use_case

import com.jskako.maps.domain.model.CustomClusterItem
import com.jskako.maps.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetClusterItems(
    private val repository: Repository
    ) {
        operator fun invoke(): Flow<List<CustomClusterItem>> {
            return repository.getBetShops()
        }
    }