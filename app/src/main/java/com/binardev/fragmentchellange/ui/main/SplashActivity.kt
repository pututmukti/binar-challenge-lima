package com.binardev.fragmentchellange.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.binardev.fragmentchellange.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, LandingPage::class.java)
            startActivity(intent)
        }, 3000)
        val placeLogo = findViewById<ImageView>(R.id.logoImage)
        val imgLogo = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"
        Glide.with(this)
            .load(imgLogo).error(R.drawable.ic_logo_image)
            .into(placeLogo)
    }
}