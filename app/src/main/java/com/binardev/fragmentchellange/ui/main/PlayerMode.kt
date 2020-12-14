package com.binardev.fragmentchellange.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.binardev.fragmentchellange.R
import com.binardev.fragmentchellange.dao.PlayerModeDao
import com.binardev.fragmentchellange.dao.impl.PlayerModeDaoImpl
import com.binardev.fragmentchellange.model.Pemain
import com.binardev.fragmentchellange.model.Suit
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_game.*


class PlayerMode : AppCompatActivity(), PlayerModeDao {
    private val imgLogo = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"
    private var dataPlayer1 = ""
    private var dataPlayer2 = ""
    private val layoutImage: LinearLayout by lazy { findViewById(R.id.activity_game) }
    private val resetFun by lazy {
        findViewById<ImageView>(R.id.imageBattle)
    }
    private var enemyNya: ImageButton? = null
    private val textName by lazy {
        mutableListOf(
            findViewById<TextView>(R.id.player1),
            findViewById(R.id.player2)
        )
    }
    private val backgroundAll by lazy {
        mutableListOf(
            findViewById<FrameLayout>(R.id.backgroundBatu), findViewById(R.id.backgroundScissors),
            findViewById(R.id.backgroundPaper), findViewById(R.id.backgroundBatuComp),
            findViewById(R.id.backgroundScissorsComp), findViewById(R.id.backgroundPaperComp),
        )
    }
    private val buttonAll by lazy {
        mutableListOf(
            findViewById(R.id.batuPlayer),
            findViewById(R.id.scissorsPlayer),
            findViewById(R.id.paperPlayer),
            findViewById(R.id.batuComp),
            findViewById(R.id.scissorsComp),
            findViewById(R.id.paperComp),
            findViewById(R.id.refreshBut),
            findViewById<ImageButton>(R.id.but_close)
        )
    }
    private val intentDialog by lazy { Intent(this, HomePage::class.java) }
    private val controller = PlayerModeDaoImpl(this)
    private val randDuration = 1000L
    private var name = mutableListOf<String>()
    private var namePlay: String = "Pemain 1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        namePlay = intent.getStringExtra("dataName").toString()
        name.add(namePlay)
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        namePlay = intent.getStringExtra("dataName").toString()
        Glide.with(this)
            .load(imgLogo).error(R.drawable.ic_logo_image)
            .into(logoImageGame)
        textName[0]?.text = namePlay
        textName[1].text = getString(R.string.Player2)
        reset()
        val butNya = mutableListOf(
            buttonAll[0], buttonAll[1], buttonAll[2], buttonAll[3], buttonAll[4], buttonAll[5]
        )
        butNya.forEachIndexed { _, imageButton ->
            imageButton.setOnClickListener {
                when (it) {
                    buttonAll[0] -> {
                        if (!backgroundAll[0].isVisible) {
                            buttonAll[0].startAnimation(animation)
                            backgroundAll[0].visibility = View.VISIBLE

                        }
                        dataPlayer1 = "batu"
                    }
                    buttonAll[1] -> {
                        if (!backgroundAll[1].isVisible) {
                            buttonAll[1].startAnimation(animation)
                            backgroundAll[1].visibility = View.VISIBLE
                        }
                        dataPlayer1 = "gunting"
                    }
                    buttonAll[2] -> {
                        if (!backgroundAll[2].isVisible) {
                            buttonAll[2].startAnimation(animation)
                            backgroundAll[2].visibility = View.VISIBLE
                        }
                        dataPlayer1 = "kertas"
                    }
                    buttonAll[3] -> {
                        if (!backgroundAll[3].isVisible) {
                            buttonAll[3].startAnimation(animation)
                            backgroundAll[3].visibility = View.VISIBLE
                        }
                        dataPlayer2 = "batu"
                    }
                    buttonAll[4] -> {
                        if (!backgroundAll[4].isVisible) {
                            buttonAll[4].startAnimation(animation)
                            backgroundAll[4].visibility = View.VISIBLE
                        }
                        dataPlayer2 = "gunting"
                    }
                    else -> {
                        if (!backgroundAll[5].isVisible) {
                            buttonAll[5].startAnimation(animation)
                            backgroundAll[5].visibility = View.VISIBLE
                        }
                        dataPlayer2 = "kertas"
                    }
                }
                if (dataPlayer1 != "" && dataPlayer2 != "") {
                    lockButtonPlay1()
                    lockButtonPlay2()
                }

                if (dataPlayer1 != "" && dataPlayer2 != "") {
                    Toast.makeText(this, "$namePlay Memilih $dataPlayer1", Toast.LENGTH_SHORT)
                        .show()
                    dataModel()
                    resetFun.animate().alpha(0f).scaleX(0.5f).scaleY(0.5f).setDuration(500).start()
                    buttonAll[7].animate().alpha(0f).scaleX(0.5f).scaleY(0.5f).setDuration(500)
                        .start()
                }
                Log.i("PlayerMode", "$namePlay memilih $dataPlayer1 dan Player 2 memilih $dataPlayer2 ")

            }
        }
        buttonAll[6].setOnClickListener {
            reset()
            Log.i("PlayerMode", "playernya klik reset")
        }
        buttonAll[7].setOnClickListener {
            onBackPressed()
            Log.i("PlayerMode", "playernya klik exit")
        }
    }

    //Pemrosesan Data
    private fun dataModel() {
        val dataMauPlayer = Pemain(dataPlayer1, dataPlayer2, "PLAYER")
        Log.i("PlayerMode", "Pemain vs Pemain")
        controller.setDataPlayer(dataMauPlayer)
        controller.chooseEnemy()
        controller.compareData()
    }

    //Button lock is process still running
    private fun lockButtonPlay1() {
        buttonAll[0].isEnabled = false
        buttonAll[1].isEnabled = false
        buttonAll[2].isEnabled = false
        Log.i("PlayerMode", "button play 1 lock")
    }

    private fun lockButtonPlay2() {
        buttonAll[3].isEnabled = false
        buttonAll[4].isEnabled = false
        buttonAll[5].isEnabled = false
        Log.i("PlayerMode", "button play 2 lock")
    }


    //unlock all button
    private fun unlockButton() {
        if (!buttonAll[0].isEnabled && !buttonAll[1].isEnabled && !buttonAll[2].isEnabled &&
            !buttonAll[3].isEnabled && !buttonAll[4].isEnabled && !buttonAll[5].isEnabled
        ) {
            buttonAll[0].isEnabled = true
            buttonAll[1].isEnabled = true
            buttonAll[2].isEnabled = true
            buttonAll[3].isEnabled = true
            buttonAll[4].isEnabled = true
            buttonAll[5].isEnabled = true
        }
    }

    //Reset all
    private fun reset() {
        mutableListOf(
            backgroundAll[0],
            backgroundAll[1],
            backgroundAll[2],
            backgroundAll[3],
            backgroundAll[4],
            backgroundAll[5],
        ).forEachIndexed { _, i ->
            if (i.isVisible) {
                i.visibility = View.INVISIBLE
                Log.i("PlayerMode", "Reset ALl")
            }
        }
        resetFun.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(randDuration).start()
        buttonAll[7].animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(randDuration).start()
        dataPlayer1 = ""
        dataPlayer2 = ""
        unlockButton()
    }

    //Random Animasi
    override fun randAnim(animResult: String) {}

    //Hasil dari Randomnya computer
    override fun resultEnemy(resultEnemy: String) {
        when (resultEnemy) {
            "batu" -> {
                enemyNya = buttonAll[3]
                backgroundAll[3].visibility = View.VISIBLE
            }
            "gunting" -> {
                enemyNya = buttonAll[4]
                backgroundAll[4].visibility = View.VISIBLE
            }
            "kertas" -> {
                enemyNya = buttonAll[5]
                backgroundAll[5].visibility = View.VISIBLE
            }
        }
        Log.e("PlayerMode", "pilihan Pemain 2 $resultEnemy")
        Toast.makeText(this, "Pemain 2 Memilih $resultEnemy", Toast.LENGTH_SHORT).show()
    }

    //Menampilkan hasil dari kalah atau menang
    override fun resultWinner(resultNya: String) {
        var winner = ""
        when (resultNya) {
            "P1Win" -> {
                winner = "$namePlay\nMENANG!"
            }
            "P2Win" -> {
                winner = "Player 2\nMENANG!"
            }
            "Seri" -> {
                winner = "SERI!"
            }
        }
        Log.i("PlayerMode", "pemenangnya $winner")
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val view = LayoutInflater.from(this).inflate(R.layout.activity_dialog, null, false)
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setView(view)
                val dialogD1 = dialogBuilder.create()
                dialogD1.setCancelable(false)
                val winnerInfo by lazy { view.findViewById<TextView>(R.id.winner) }
                val playAgain by lazy { view.findViewById<Button>(R.id.play_again) }
                val backMenu by lazy { view.findViewById<Button>(R.id.back_menu) }
                winnerInfo.text = winner
                playAgain.setOnClickListener {
                    reset()
                    dialogD1.dismiss()
                }
                backMenu.setOnClickListener {

                    intentDialog.putExtra("dataName", name[0])
                    startActivity(intentDialog)
                }
                dialogD1.show()
            }, 2 * randDuration
        )
    }

    override fun onBackPressed() {
        val snackPlayer = Snackbar.make(layoutImage, "Apakah ingin keluar?", Snackbar.LENGTH_SHORT)
        snackPlayer.setAction("Keluar") {
            snackPlayer.dismiss()
            finish()
        }.show()
        Log.i("PlayerMode", "Keluar")
    }
}