/*
 * Copyright (C) 2023. Bundesdruckerei GmbH
 */

package com.jskako.datastore_encryption.di.modules

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jskako.datastore_encryption.preferences.DATASTORE_NAME
import com.jskako.datastore_encryption.preferences.DefaultPreference
import com.jskako.datastore_encryption.preferences.DefaultPreferenceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DATASTORE_NAME,
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(context, DATASTORE_NAME)
        )
    }
)

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDataStore(
        app: Application
    ): DataStore<Preferences> {
        return app.dataStore
    }

    @Provides
    @Singleton
    fun provideDefaultPreference(dataStore: DataStore<Preferences>): DefaultPreferenceProvider {
        return DefaultPreference(dataStore)
    }
}
