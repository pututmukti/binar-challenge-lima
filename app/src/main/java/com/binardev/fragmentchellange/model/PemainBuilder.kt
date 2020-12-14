package com.binardev.fragmentchellange.model

class PemainBuilder {
    private var pemain : Pemain?=null

    fun setPemain(pemain: Pemain): PemainBuilder {
        this.pemain = pemain
        return this
    }
}