package com.jskako.maps.domain.use_case

import com.jskako.maps.domain.connectivity.ConnectivityObserver
import kotlinx.coroutines.flow.Flow

class GetNetworkStatus(
    private val connectivityObserver: ConnectivityObserver
) {
    operator fun invoke(
    ): Flow<ConnectivityObserver.Status> {
        return connectivityObserver.observe()
    }
}