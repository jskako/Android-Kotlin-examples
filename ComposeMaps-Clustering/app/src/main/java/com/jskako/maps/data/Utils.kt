package com.jskako.maps.data

import kotlin.random.Random

internal fun generateMockedPhoneNumber(): String {
    // Choose a random area code
    val areaCode = Random.nextInt(100, 1000).toString()

    // Choose a random exchange code
    val exchangeCode = Random.nextInt(100, 1000).toString()

    // Choose the remaining four digits
    val remainingDigits = Random.nextInt(1000, 10000)

    // Combine the parts to form the phone number
    return "$areaCode-$exchangeCode-$remainingDigits"
}