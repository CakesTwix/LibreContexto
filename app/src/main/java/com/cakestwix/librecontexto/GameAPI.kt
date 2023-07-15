package com.cakestwix.librecontexto

import android.util.Log
import com.cakestwix.librecontexto.models.GameModel
import com.cakestwix.librecontexto.models.TopWordsListModel
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class GameAPI(gameId: String, lang : String = "en") {
    private val apiUrl = "https://api.contexto.me/machado/$lang"
    private val apiAskWord = "$apiUrl/game/$gameId"
    private val apiTopWordList = "$apiUrl/top/$gameId"

    private val client = OkHttpClient()
    val json = Json { var ignoreUnknownKeys = true }

    private var TopWordList : TopWordsListModel
    init {
        val request = Request.Builder()
            .url(apiTopWordList)
            .build()

        val responseBody: String = client.newCall(request).execute().body.string()
        // Log.d("CakesTwix-Debug", responseBody)
        TopWordList = json.decodeFromString(TopWordsListModel.serializer(), responseBody)
        // Log.d("CakesTwix-Debug", TopWordList.words[0])
    }

    fun askWord(word: String): Int {
        TopWordList.words.forEachIndexed { index, s ->
            if (s == word){
                return index + 1
            }
        }
        
        val request = Request.Builder()
            .url("${apiAskWord}/$word")
            .build()
        val response: Response = client.newCall(request).execute()
        val responseBody: String = response.body.string()
        Log.d("CakesTwix-Debug", response.code.toString())
        if (response.code == 200) {
            return json.decodeFromString(GameModel.serializer(), responseBody).distance + 1
        } else if (response.code == 404) {
            return 0
        } else {
            Log.d("CakesTwix-Debug", "Request failed")
        }
        return 0
    }
}