package com.jskako.cleanarchitecturenoteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jskako.cleanarchitecturenoteapp.ui.theme.CleanArchitectureNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNoteAppTheme {

            }
        }
    }
}
