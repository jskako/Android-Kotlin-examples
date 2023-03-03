/*
 *  Copyright (C) 2023. Bundesdruckerei GmbH
 */

package com.jskako.datastore_encryption.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DefaultPreferenceProvider {
    fun getString(key: Preferences.Key<String>): Flow<String>
    suspend fun saveString(key: Preferences.Key<String>, value: String)
    suspend fun deleteString(key: Preferences.Key<String>)
    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean>
}