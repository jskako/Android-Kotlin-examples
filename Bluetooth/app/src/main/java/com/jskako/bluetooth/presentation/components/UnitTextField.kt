package com.jskako.bluetooth.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UnitTextField(
    firstText: String,
    secondText: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = firstText, style = TextStyle(
                fontSize = 20.sp
            ), modifier = Modifier
                .width(IntrinsicSize.Max)
                .alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = secondText,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}