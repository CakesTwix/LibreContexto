package com.cakestwix.librecontexto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.cakestwix.librecontexto.databinding.SettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: SettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.buttonSecond.setOnClickListener {
        //    findNavController().navigate(R.id.action_HowtoPlayToMenu)
        //}

        // Add back button for ToolBar
        (activity as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_MenuFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}