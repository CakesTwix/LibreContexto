package com.cakestwix.librecontexto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cakestwix.librecontexto.R

data class Word(val distance: Int, val word: String)

class WordRecyclerAdapter(private val word: List<Word>) :
    RecyclerView.Adapter<WordRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val word: TextView = itemView.findViewById(R.id.word)
        val distance: TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_word, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = word.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.word.text = word[position].word
        holder.distance.text = word[position].distance.toString()
    }
}