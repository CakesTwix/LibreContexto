package com.cakestwix.librecontexto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cakestwix.librecontexto.databinding.GameBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NUM = "num"

class GameFragment : Fragment() {
    private var num: String? = null

    private var _binding: GameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            num = it.getString(ARG_NUM)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = GameBinding.inflate(inflater, container, false)

        binding.gameId.text = num

        return binding.root
    }
}