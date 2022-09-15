package domain
/*
 *  Copyright (C) 2022. jskako
 */

import data.LogDetails
import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.swing.JFileChooser
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import presentation.getLogManager


fun getTimeStamp(format: String = "HH:mm:ss"): String = DateTimeFormatter
    .ofPattern(format)
    .withZone(ZoneOffset.UTC)
    .format(LocalDateTime.now())


fun emptyLine(numberOfEmptyLines: Int = 1): String {
    var buildMsg = ""
    repeat(numberOfEmptyLines) {
        buildMsg += "\n"
    }
    return buildMsg
}

fun getCustomCoroutineScope() = CoroutineScope(
    CoroutineName("TestScope")
            + Dispatchers.Default
            + SupervisorJob()
            + CoroutineExceptionHandler { _, exception ->
        getLogManager().addLog(
            "CoroutineExceptionHandler got: ${exception.message}"
        )
    }
)

fun copyToClipboard(data: String) {
    if (data.trim().isNotEmpty()) {
        val stringSelection = StringSelection(data)
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)
        getLogManager().addLog("Copied to clipboard successfully", false)
    } else {
        getLogManager().addLog("There is nothing to copy", false)
    }
}

fun prepareLogs(logs: List<LogDetails>): String {
    var stringBuilder = ""
    for (log in logs) {
        stringBuilder += log.time + emptyLine() + log.log + emptyLine(2)
    }
    return stringBuilder.trim()
}

fun exportData(
    fileName: String = getTimeStamp("yyMMdd-HHmm-ss") + "-twf-test-tool" + ".log",
    data: String,
    path: String = ""
) {
    if (data.trim().isNotEmpty()) {
        try {
            var saveLocation: String
            if (path.isEmpty()) {
                val chooser = JFileChooser()
                chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                chooser.showSaveDialog(null)
                saveLocation = chooser.selectedFile.absolutePath
                saveLocation = if(!saveLocation.isNullOrEmpty()) {
                    "${saveLocation}\\$fileName"
                } else {
                    ""
                }
            } else {
                saveLocation = path
            }
            if (saveLocation.isNotEmpty()) {
                val myWriter = PrintWriter(saveLocation, "UTF-8")
                myWriter.write(data)
                myWriter.close()
                getLogManager().addLog("Successfully wrote to the file.\nFilename: $saveLocation", false, saveLocation)
            } else {
                getLogManager().addLog("Error: No save location specified.", false)
            }
        } catch (e: IOException) {
            getLogManager().addLog("An error occurred while writing to file.\n ${e.printStackTrace()}", false)
        }
    } else {
        getLogManager().addLog("No data to export.", false)
    }
}

fun openFile(path: String) {
    val isDesktopSupported = Desktop.isDesktopSupported()
    if (isDesktopSupported) {
        val file = File(path)
        val desktop = Desktop.getDesktop()
        if (file.exists()) {
            desktop.open(file)
            getLogManager().addLog("File opened: $path", false)
        }
    } else {
        getLogManager().addLog("We cannot open file for You, not supported.", false)
    }
}