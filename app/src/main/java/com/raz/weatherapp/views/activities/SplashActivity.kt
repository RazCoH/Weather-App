package com.raz.weatherapp.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raz.weatherapp.R
import com.raz.weatherapp.objects.ScreensNavigation
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val delayTime = 4000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity_layout)
        setDelayAndPushFruitsFragment()
    }

    private fun setDelayAndPushFruitsFragment() {
        Timer().schedule(delayTime) {
            ScreensNavigation.pushMainActivity(this@SplashActivity)
            finish()
        }
    }
}