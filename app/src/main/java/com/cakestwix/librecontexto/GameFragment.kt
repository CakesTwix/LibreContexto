package com.cakestwix.librecontexto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cakestwix.librecontexto.adapter.Word
import com.cakestwix.librecontexto.adapter.WordRecyclerAdapter
import com.cakestwix.librecontexto.databinding.GameBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NUM = "num"

class GameFragment : Fragment() {
    private var num: String? = null

    private var _binding: GameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var words = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            num = it.getString(ARG_NUM)
        }
        // Thread { GameAPI(num.toString()).askWord("Test") }.start()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = GameBinding.inflate(inflater, container, false)

        // binding.gameId.text = num
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordRecyclerAdapter(words)

        binding.doWord.setOnClickListener {
            Thread {
                if(words.any { it.word.toLowerCase() == binding.wordEdit.text.toString().toLowerCase()}) {
                    return@Thread
                }
                val askedWord = GameAPI(num.toString()).askWord(binding.wordEdit.text.toString().toLowerCase())
                if(askedWord != 0){
                    words.add(Word(askedWord, binding.wordEdit.text.toString()))
                }

                activity?.runOnUiThread {
                    if(askedWord == 1){
                        Toast.makeText(activity, "Congratulations! You guessed the word", Toast.LENGTH_LONG).show()
                    }
                    recyclerView.adapter = WordRecyclerAdapter(words.sortedBy { it.distance })
                }
            }.start()
        }
        return binding.root
    }
}