package com.gamingingrs.splashscreenapi

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gamingingrs.splashscreenapi.databinding.ActivityMainBinding
import com.gamingingrs.splashscreenapi.utils.delay

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var keep = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initSplashScreen() {
        val splashScreen = installSplashScreen()
        // Keep SplashScreen alive until keep equals true
        splashScreen.setKeepOnScreenCondition { keep }

        delay(SPLASH_SCREEN_DURATION) {
            keep = false
        }

        setSplashAnimation()
    }

    private fun setSplashAnimation() {
        // This is valid only for API 31 or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 500L
                slideUp.doOnEnd { splashScreenView.remove() }
                slideUp.start()
            }
        }
    }

}

private const val SPLASH_SCREEN_DURATION = 2000L