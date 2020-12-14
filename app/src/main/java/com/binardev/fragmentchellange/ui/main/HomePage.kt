package com.binardev.fragmentchellange.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.binardev.fragmentchellange.R
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {
    private val animation by lazy{
        AnimationUtils.loadAnimation(this, R.anim.bounce)
    }
    private val buttonPlay by lazy {
        mutableListOf(
            findViewById<ImageButton>(R.id.butPlayVsPlay), findViewById(R.id.butPlayVsComp)
        )
    }
    private val intentNya by lazy {
        mutableListOf(
            Intent(this@HomePage, PlayerMode::class.java),
            Intent(this@HomePage, ComputerMode::class.java)
        )
    }
    private var namePlay: String = "Pemain1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        namePlayerComp.text = intent.extras?.getString("dataName")
        namePlayerPlayer.text = intent.extras?.getString("dataName")
        namePlay = intent.extras?.getString("dataName").toString()
        val butNya = mutableListOf(
            buttonPlay[0], buttonPlay[1]
        )
        butNya.forEachIndexed { _, imageButton ->
            imageButton.setOnClickListener {
                when (it) {
                    buttonPlay[0] -> {
                        buttonPlay[0].startAnimation(animation)
                        intentNya[0].putExtra("dataName", namePlay)
                        startActivity(intentNya[0])
                    }
                    buttonPlay[1] -> {
                        buttonPlay[1].startAnimation(animation)
                        intentNya[1]
                        intentNya[1].putExtra("dataName", namePlay)
                        startActivity(intentNya[1])
                    }
                }
            }
        }
        Log.i("HomePage", "Memilih lawan dan lawanya adalah $intentNya")
    }

    override fun onStart() {
        super.onStart()
        val snackPlayer = Snackbar.make(mainMenu, "Selamat Datang $namePlay", Snackbar.LENGTH_INDEFINITE)
        snackPlayer.setAction("Tutup") {
            snackPlayer.dismiss()
        }.show()
        Log.i("HomePage", "Munculnya snackbar Selamat datang")
    }

    override fun onBackPressed() {
        val snackPlayer = Snackbar.make(mainMenu, "Yakin $namePlay ingin keluar dari game ini?", Snackbar.LENGTH_LONG)
        snackPlayer.setAction("Keluar") {
            snackPlayer.dismiss()
            finishAffinity()
        }.show()
        Log.i("HomePage", "Keluar")

    }
}