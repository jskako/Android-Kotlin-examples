package com.jskako.maps.presentation.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jskako.maps.presentation.ui.screens.map.states.MarkerInfo

@Composable
fun MarkerInfoSection(
    modifier: Modifier = Modifier,
    visible: Boolean,
    markersInfo: List<MarkerInfo>,
    onNavigate: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Column(
                modifier = modifier
                    .padding(8.dp)
            ) {
                markersInfo.forEach { (title, icon) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        RowItem(title, icon)
                    }
                }
                ActionIconButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    buttonContainerColor = Color.DarkGray,
                    text = "Navigate",
                    icon = Icons.Default.LocationOn,
                    onClick = onNavigate
                )
            }
        }
    }
}