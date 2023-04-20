package com.jskako.maps.domain.use_case

import com.jskako.maps.domain.model.BetShop
import com.jskako.maps.domain.model.Position
import com.jskako.maps.domain.repository.Repository

class SearchBetShop(
    private val repository: Repository
) {
    suspend operator fun invoke(
        position: Position
    ): Result<List<BetShop>> {
        return if (position.isValid()) {
            Result.success(emptyList())
        } else {
            repository.searchBetShops(position)
        }
    }
}