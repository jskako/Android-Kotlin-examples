package com.gamingingrs.splashscreenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamingingrs.splashscreenapi.databinding.ActivityMainBinding
import com.gamingingrs.splashscreenapi.utils.splash.SplashScreen
import com.gamingingrs.splashscreenapi.utils.splash.SplashScreenListener
import com.gamingingrs.splashscreenapi.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initSplashScreen() {
        val splashScreen = SplashScreen(this)
        splashScreen.init() // With ending animation (for API 31 or above)
        //splashScreen.init(splashScreenAnimation = false) // Without ending animation
        splashScreen.setListener(object : SplashScreenListener {
            override fun onError(msg: String) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(msg: String) {
                msg.toast(applicationContext)
            }

        })
    }

}