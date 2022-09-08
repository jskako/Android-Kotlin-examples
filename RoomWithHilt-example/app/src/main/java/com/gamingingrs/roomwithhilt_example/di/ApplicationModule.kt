package com.gamingingrs.roomwithhilt_example.di

import com.gamingingrs.roomwithhilt_example.data.dao.AnimalsDao
import com.gamingingrs.roomwithhilt_example.data.repository.AnimalsDataSource
import com.gamingingrs.roomwithhilt_example.data.repository.AnimalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideAnimalsRepository(animalsDao: AnimalsDao): AnimalsRepository =
        AnimalsDataSource(animalsDao)

}