package com.jskako.maps.domain.use_case

import com.jskako.maps.domain.model.BetShop
import com.jskako.maps.domain.repository.Repository

class InsertBetShop(
    private val repository: Repository
) {
    suspend operator fun invoke(
        betShops: List<BetShop>
    ) {
        repository.insertBetShops(betShops)
    }
}