package presentation
/*
 *  Copyright (C) 2021. Bundesdruckerei GmbH
 */

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import data.LogDetails
import domain.copyToClipboard
import domain.openFile

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun addTextInput(label: String = "", defaultText: String = "", enabled: Boolean = true): String {
    var text by rememberSaveable { mutableStateOf(defaultText) }
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = Modifier
            .onPreviewKeyEvent {
                if (it.key == Key.Tab) {
                    focusManager.moveFocus(FocusDirection.Next)
                    true
                } else {
                    false
                }
            }
            .focusable(),
        value = text,
        enabled = enabled,
        onValueChange = {
            text = it
        },
        label = { Text(label) },
    )
    return text
}

@Composable
fun addSpacer(size: Dp = 10.dp) {
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun logViewComponent(logs: List<LogDetails>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .padding(bottom = 16.dp),
    ) {
        items(
            logs
        ) {
            logCard(log = it)
        }
    }
}

@Composable
fun logCard(log: LogDetails) {
    val color = if (log.addToHistory) {
        Color.Yellow
    } else {
        Color.LightGray
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = color,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = log.time ?: "",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = log.log ?: "",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
            }
            if (log.pathToDocument.isNotEmpty()) {
                Column {
                    Button(
                        onClick = {
                            openFile(log.pathToDocument)
                        },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 0.dp),
                    ) {
                        Text("Open")
                    }

                    Button(
                        onClick = {
                            copyToClipboard(log.pathToDocument)

                        },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 0.dp),
                    ) {
                        Text("Copy path")
                    }
                }
            }
        }
    }
}
