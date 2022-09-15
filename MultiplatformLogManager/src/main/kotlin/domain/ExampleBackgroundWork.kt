package domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import presentation.getLogManager

private val logManager = getLogManager()
private val scope = getCustomCoroutineScope()

fun doSomeBackgroundWork(name: String, surname: String) {
    scope.launch {
        logManager.addLog("Hey $name")
        delay(500L)
        logManager.addLog("Is your surname $surname?")
        delay(500L)
        logManager.addLog("Well, it's nice to meet you $name $surname?")
    }
}