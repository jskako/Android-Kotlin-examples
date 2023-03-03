package com.jskako.datastore_encryption

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.datastore_encryption.preferences.DefaultPreferenceProvider
import com.jskako.datastore_encryption.preferences.TEST_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceProvider: DefaultPreferenceProvider
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun saveString(key: Preferences.Key<String>, value: String) = viewModelScope.launch {
        preferenceProvider.saveString(key, value)
        _eventFlow.emit(
            UiEvent.ShowSnackbar(
                message = if (value.isEmpty()) {
                    "Cannot save empty data"
                } else {
                    "String $value pushed to DataStore"
                }
            )
        )
    }

    fun getString(key: Preferences.Key<String>) = viewModelScope.launch {
        _eventFlow.emit(
            UiEvent.ShowSnackbar(
                message = if (preferenceProvider.isKeyStored(TEST_STRING).first()) {
                    val value = preferenceProvider.getString(key).first()
                    "String $value pulled from DataStore"
                } else {
                    "Nothing to pull from DataStore"
                }
            )
        )
    }

    fun deleteString(key: Preferences.Key<String>) = viewModelScope.launch {
        _eventFlow.emit(
            UiEvent.ShowSnackbar(
                message = if (preferenceProvider.isKeyStored(TEST_STRING).first()) {
                    preferenceProvider.deleteString(key)
                    "Data deleted from DataStore"
                } else {
                    "Nothing to delete from DataStore"
                }
            )
        )
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }

}