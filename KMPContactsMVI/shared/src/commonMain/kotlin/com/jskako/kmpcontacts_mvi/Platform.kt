package com.jskako.kmpcontacts_mvi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform