package com.jskako.kmpcontacts_mvi.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.jskako.kmpcontacts_mvi.ui.theme.DarkColorScheme
import com.jskako.kmpcontacts_mvi.ui.theme.LightColorScheme
import com.jskako.kmpcontacts_mvi.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}