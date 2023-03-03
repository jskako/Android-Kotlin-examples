package com.jskako.datastore_encryption

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jskako.datastore_encryption.navigation.Route
import com.jskako.datastore_encryption.ui.MainScreen
import com.jskako.datastore_encryption.ui.theme.DatastoreEncryptionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatastoreEncryptionTheme {
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
                        startDestination = Route.MAIN
                    ) {
                        composable(Route.MAIN) {
                            MainScreen(
                                snackbarHostState = snackbarHostState
                            )
                        }
                    }
                }
            }
        }
    }
}