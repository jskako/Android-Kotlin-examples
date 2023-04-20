package com.jskako.maps.domain.di

import android.content.Context
import com.jskako.maps.domain.connectivity.ConnectivityObserver
import com.jskako.maps.domain.connectivity.NetworkConnectivityObserver
import com.jskako.maps.domain.repository.Repository
import com.jskako.maps.domain.use_case.UseCases
import com.jskako.maps.domain.use_case.InsertBetShop
import com.jskako.maps.domain.use_case.SearchBetShop
import com.jskako.maps.domain.use_case.GetClusterItems
import com.jskako.maps.domain.use_case.GetNetworkStatus
import com.jskako.maps.domain.use_case.GetDeviceLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideNetworkConnectivity(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: Repository,
        connectivityObserver: ConnectivityObserver
    ): UseCases {
        return UseCases(
            insertBetShop = InsertBetShop(repository),
            searchBetShop = SearchBetShop(repository),
            getClusterItems = GetClusterItems(repository),
            getNetworkStatus = GetNetworkStatus(connectivityObserver),
            getDeviceLocation = GetDeviceLocation()
        )
    }
}