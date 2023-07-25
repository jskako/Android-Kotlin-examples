package com.jskako.kmpcontacts_mvi.core.presentation

import androidx.compose.runtime.Composable

expect class ImagePickerFactory {

    @Composable
    fun createPicker(): ImagePicker
}