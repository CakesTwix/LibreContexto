package com.cakestwix.librecontexto.utils

import java.text.SimpleDateFormat
import java.util.Date

val simpleDate = SimpleDateFormat("dd/M/yyyy")
val firstGame = simpleDate.parse("18/9/2022")

class gameDate {

    fun getLastNumGame(): String {
        val mDifference = kotlin.math.abs(Date().time - firstGame!!.time) / (24 * 60 * 60 * 1000)
        return mDifference.toString()
    }

    fun getNumGame(unix_stamp: Long): String {
        val mDifference = (unix_stamp - firstGame!!.time) / (24 * 60 * 60 * 1000)
        return mDifference.toString()
    }

}