package com.jskako.maps.domain.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class Utils {

    fun getCurrentTime(format: String = "HHmm"): Int {
        val formatter = DateTimeFormatter.ofPattern(format)
        val formattedTime = LocalDateTime.now().format(formatter)
        return try {
            formattedTime.toInt()
        } catch (nfe: NumberFormatException) {
            0
        }
    }
}