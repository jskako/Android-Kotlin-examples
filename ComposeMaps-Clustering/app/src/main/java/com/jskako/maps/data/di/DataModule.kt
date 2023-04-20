package com.jskako.maps.data.di

import android.app.Application
import androidx.room.Room
import com.jskako.maps.data.local.Database
import com.jskako.maps.data.remote.BetShopsApi
import com.jskako.maps.data.remote.BetShopsApi.Companion.BASE_URL
import com.jskako.maps.data.repository.RepositoryImpl
import com.jskako.maps.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideBetShopApi(client: OkHttpClient): BetShopsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBetShopRepository(
        api: BetShopsApi,
        db: Database
    ): Repository {
        return RepositoryImpl(
            dao = db.dao,
            api = api
        )
    }
}

private const val DATABASE_NAME = "betshop_db"