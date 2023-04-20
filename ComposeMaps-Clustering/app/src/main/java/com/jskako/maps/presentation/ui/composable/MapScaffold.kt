package com.jskako.maps.presentation.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MapScaffold(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onSearchClick: () -> Unit,
    onLocationClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        Row {
            ActionIconButton(
                text = "Search",
                icon = Icons.Default.Search,
                iconDescription = "Search",
                modifier = Modifier.padding(8.dp),
                enabled = enabled,
                onClick = {
                    onSearchClick()
                }
            )

            ActionIconButton(
                text = "",
                icon = Icons.Default.LocationOn,
                iconDescription = "Location",
                modifier = Modifier.padding(8.dp),
                enabled = enabled,
                onClick = {
                    onLocationClick()
                }
            )
        }
    }
}