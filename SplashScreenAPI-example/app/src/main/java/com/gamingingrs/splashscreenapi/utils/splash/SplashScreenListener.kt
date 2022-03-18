package com.gamingingrs.splashscreenapi.utils.splash

interface SplashScreenListener {
    fun onError(msg: String)
    fun onSuccess(msg: String)
}