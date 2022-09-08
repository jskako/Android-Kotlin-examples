package com.gamingingrs.roomwithhilt_example.di

import android.content.Context
import androidx.room.Room
import com.gamingingrs.roomwithhilt_example.data.Database
import com.gamingingrs.roomwithhilt_example.data.dao.AnimalsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "database.db"
        ).build()
    }

    @Provides
    fun provideAnimalsDao(database: Database): AnimalsDao {
        return database.animalsDao()
    }

}