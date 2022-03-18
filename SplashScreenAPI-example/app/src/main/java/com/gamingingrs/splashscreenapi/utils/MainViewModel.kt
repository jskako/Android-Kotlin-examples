package com.gamingingrs.splashscreenapi.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var _splashScreenNotDone = MutableLiveData(true)
    val splashScreenNotDone: LiveData<Boolean> = _splashScreenNotDone

    fun endSplashScreen() = run { _splashScreenNotDone.value = false }

}