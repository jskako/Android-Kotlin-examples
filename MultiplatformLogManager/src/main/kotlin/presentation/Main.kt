package presentation
/*
*  Copyright (C) 2022. jskako
*/

import domain.LogManager
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import domain.copyToClipboard
import domain.doSomeBackgroundWork
import domain.exportData
import domain.getCustomCoroutineScope
import domain.prepareLogs
import kotlinx.coroutines.launch

private val logManager = LogManager()
private val scope = getCustomCoroutineScope()

@Composable
@Preview
fun MainUI() {
    Column(Modifier.fillMaxWidth().fillMaxHeight()) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(15.dp)
                .background(Color.White)
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val name = addTextInput(
                label = "Name",
                defaultText = "Josip"
            )
            addSpacer()
            val surname = addTextInput(
                label = "Surname",
                defaultText = "Skako"
            )
            addSpacer(20.dp)

            MaterialTheme {
                Button(
                    onClick = {
                        doSomeBackgroundWork(name, surname)
                    }
                ) {
                    Text("Do some background work")
                }
            }
        }
        addLogInputUI()
    }
}

@Composable
private fun addLogInputUI() {
    Divider(color = Color.Gray, thickness = 1.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .background(Color.White)
            .padding(16.dp)
    ) {
        MaterialTheme {
            Row {
                Button(
                    onClick = {
                        scope.launch {
                            copyToClipboard(prepareLogs(logManager.logRecord.value.reversed()))
                        }
                    },
                    modifier = Modifier
                        .padding(start = 25.dp, end = 0.dp),
                ) {
                    Text("Copy logs")
                }

                Button(
                    onClick = {
                        scope.launch {
                            exportData(data = prepareLogs(logManager.logRecord.value.reversed()))
                        }
                    },
                    modifier = Modifier
                        .padding(start = 10.dp, end = 0.dp),
                ) {
                    Text("Export logs")
                }
                Button(
                    onClick = {
                        scope.launch {
                            logManager.clearLogs()
                        }
                    },
                    modifier = Modifier
                        .padding(start = 10.dp, end = 20.dp),
                ) {
                    Text("Clear logs")
                }
            }
        }
        logViewComponent(logManager.logRecord.value.reversed())
    }
}

fun getLogManager() = logManager

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainUI()
    }
}
