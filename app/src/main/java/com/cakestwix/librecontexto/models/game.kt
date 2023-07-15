package com.cakestwix.librecontexto.models

import kotlinx.serialization.Serializable

@Serializable
data class GameModel (
    val distance : Int,
    val lemma : String,
    val word : String,
)

@Serializable
data class TopWordsListModel (
    val words: List<String>
)