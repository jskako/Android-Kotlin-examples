package com.gamingingrs.splashscreenapi.utils.splash

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gamingingrs.splashscreenapi.utils.delay

class SplashScreen(private val activity: Activity) {

    private var listener: SplashScreenListener? = null
    private var splashScreenAlive = true

    fun init(splashScreenAnimation: Boolean = true) {
        val splashScreen = activity.installSplashScreen()
        // Keep SplashScreen alive until keep equals true
        splashScreen.setKeepOnScreenCondition { splashScreenAlive }

        delay(SPLASH_SCREEN_DURATION) {
            splashScreenAlive = false
            listener?.onSuccess("SplashScreen ended successfully")
        }

        if (splashScreenAnimation) {
            setSplashAnimation()
        }
    }

    private fun setSplashAnimation() {
        // This is valid only for API 31 or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            activity.splashScreen.setOnExitAnimationListener { splashScreenView ->
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

    fun setListener(listener: SplashScreenListener) {
        this.listener = listener
    }

}

private const val SPLASH_SCREEN_DURATION = 2000L