package data

data class LogDetails(
    val time: String? = "",
    val log: String? = "",
    /**
     *  History not implemented
     *  This parameter can be used if you don't want to add log in history
     *  Example: You don't want to add "Application started" in history
     */
    val addToHistory: Boolean = true,
    val pathToDocument: String = ""
)