package com.jskako.datastore_encryption.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jskako.datastore_encryption.MainViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.jskako.datastore_encryption.components.ActionButton
import com.jskako.datastore_encryption.preferences.TEST_STRING

@Composable
fun MainScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is MainViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionButton(
                text = "Save to DataStore",
                onClick = { viewModel.saveString(TEST_STRING, "MyRandomString") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(
                text = "Get from DataStore",
                onClick = { viewModel.getString(TEST_STRING) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(
                text = "Delete from DataStore",
                onClick = { viewModel.deleteString(TEST_STRING) },
            )
        }
    }
}