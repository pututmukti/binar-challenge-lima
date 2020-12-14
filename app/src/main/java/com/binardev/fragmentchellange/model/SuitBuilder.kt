package com.binardev.fragmentchellange.model

class SuitBuilder {
    private var suitResult : Suit?=null

    var suitResultBase = ArrayList<Suit>()

    fun setSuitResult(suitResult: Suit): SuitBuilder {
        this.suitResult = suitResult
        return this
    }

    fun addSuitResult(suitResultItem: Suit): SuitBuilder {
        suitResultBase.add(suitResultItem)
        return this
    }
}