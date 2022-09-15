package domain
/*
 *  Copyright (C) 2022. jskako
 */

import androidx.compose.runtime.mutableStateOf
import data.LogDetails
import kotlinx.coroutines.launch

class LogManager {

    private val scope = getCustomCoroutineScope()
    val logRecord = mutableStateOf(listOf(LogDetails(getTimeStamp(), "Application started.", false)))

    fun addLog(log: String, addToLogHistory: Boolean = true, pathDoDocument: String = "") {
        scope.launch {
            logRecord.value += (listOf(LogDetails(getTimeStamp(), log, addToLogHistory, pathDoDocument)))
        }
        println(logRecord.value)
    }

    fun clearLogs() {
        logRecord.value = listOf(LogDetails(getTimeStamp(), "Log view cleared", false))
    }

}