package com.binardev.fragmentchellange.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.binardev.fragmentchellange.R
import kotlinx.android.synthetic.main.activity_landing_page.*


class LandingPage : AppCompatActivity() {
    private var name = ""
    private lateinit var buttonHide: ViewPager2.OnPageChangeCallback
    private val animation by lazy{
        AnimationUtils.loadAnimation(this, R.anim.bounce)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_landing_page)
        val pagerAdapter = LandingPageAdapter(this) { name = it.toString() }
        slideLandingPage.adapter = pagerAdapter
        dots_indicator.setViewPager2(slideLandingPage)
        buttonHide = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    btnNameInput.visibility = View.VISIBLE
                } else {
                    btnNameInput.visibility = View.GONE
                }
            }
        }
        slideLandingPage.registerOnPageChangeCallback(buttonHide)

        btnNameInput.setOnClickListener {
            btnNameInput.startAnimation(animation)
            when {
                name != "" -> {
                    Intent(this, MainMenu::class.java)
                        .apply {
                            putExtra("dataName", name)
                            startActivity(this)
                        }
                }
                else -> {
                    Toast.makeText(this, "Silahkan isi namanya terlebih dahulu!", Toast.LENGTH_SHORT).show()
                }
            }
            Log.i("LandingPage", "klik button untuk input nama")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        slideLandingPage.registerOnPageChangeCallback(buttonHide)
        Log.i("LandingPage", "Destroy button hide")
    }

    override fun onBackPressed() {
        finishAffinity()
        Log.i("LandingPage", "Keluar")
    }
}