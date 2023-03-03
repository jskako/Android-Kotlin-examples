package com.jskako.datastore_encryption.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultPreference(
    private val dataStore: DataStore<Preferences>
) : DefaultPreferenceProvider {

    override fun getString(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: ""
        }
    }

    override suspend fun saveString(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun deleteString(key: Preferences.Key<String>) {
        dataStore.edit { preferences ->
            preferences.remove(TEST_STRING)
        }
    }

    override fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences.contains(TEST_STRING)
        }
    }

}