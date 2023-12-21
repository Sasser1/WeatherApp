package com.example.vremenskaprognoza

import android.R.layout
import android.os.Handler
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish() // Close the splash activity
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    companion object {
        private const val SPLASH_DISPLAY_LENGTH = 2000 // 2 seconds
    }
}
