package com.binardev.fragmentchellange.dao.impl

import android.util.Log
import com.binardev.fragmentchellange.dao.PlayerModeDao
import com.binardev.fragmentchellange.model.Pemain
import com.binardev.fragmentchellange.model.Suit
import com.binardev.fragmentchellange.model.SuitBuilder

class PlayerModeDaoImpl(private val listener: PlayerModeDao) {
    private var player: Pemain? = null
    private var dataCompRand = arrayListOf("batu", "gunting","kertas")
    private var compRandData = dataCompRand.random()
    var suitResultBase = ArrayList<Suit>()

    fun setDataPlayer(player: Pemain) {
        this.player = player
        Log.i("PlayerModeDao", "game mode ${player.modeGame}")
        Log.i("PlayerModeDao", "Pemain 1 choisee ${player.pemain1} ")
        Log.i("PlayerModeDao", "Pemain 2 choisee${player.pemain2} ")
    }

    fun compRand() {
        val dataCompFix=dataCompRand.random()
        listener.randAnim(dataCompFix)
    }
    fun chooseEnemy(){
        if (player!!.modeGame=="CPU"){
            listener.resultEnemy(compRandData)
            Log.i("PlayerModeDao", "CPU memilih = $compRandData")
        }
        else{
            listener.resultEnemy(player!!.pemain2)
            Log.i("PlayerModeDao", "Pemain 2 memilih = ${player!!.pemain2}")
        }
    }
    fun compareData() {
        val serach: String= if(player!!.modeGame=="CPU"){
            compRandData
        }else{
            player!!.pemain2
        }

        suitResultBase.add(Suit("gunting","kertas"))
        suitResultBase.add(Suit("batu","gunting"))
        suitResultBase.add(Suit("kertas","batu"))

        val item = suitResultBase.find { it.nodeBase == serach }

        val hasil : String = if(player!!.pemain1 == player!!.pemain2){
            "Seri"
        }else if(player!!.pemain2 == item!!.nodeWinner){
            "P1Win"
        }else{
            "P2Win"
        }

        listener.resultWinner(hasil)
        Log.i("PlayerModeDao", "Result Suit $hasil")
    }
}