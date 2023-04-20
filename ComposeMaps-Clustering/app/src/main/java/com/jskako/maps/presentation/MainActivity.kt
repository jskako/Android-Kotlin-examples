package com.jskako.maps.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jskako.maps.presentation.navigation.Route.MAP_SCREEN
import com.jskako.maps.presentation.ui.screens.map.MapScreen
import com.jskako.maps.presentation.ui.theme.MapsTheme
import com.jskako.maps.presentation.ui.utils.setAppearanceLightStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MapsTheme {
                setAppearanceLightStatusBar(isSystemInDarkTheme())
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) {
                    NavHost(
                        modifier = Modifier
                            .padding(it),
                        navController = navController,
                        startDestination = MAP_SCREEN
                    ) {
                        composable(MAP_SCREEN) {
                            MapScreen(snackbarHostState)
                        }
                    }
                }
            }
        }
    }
}