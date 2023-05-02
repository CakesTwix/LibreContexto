package com.cakestwix.librecontexto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cakestwix.librecontexto.databinding.MenuBinding
import com.cakestwix.librecontexto.utils.gameDate
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.SimpleDateFormat
import java.util.Date


class MenuFragment : Fragment() {

    private var _binding: MenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todayNum.text = getString(R.string.today_num, gameDate().getLastNumGame())

        binding.startGame.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("num", gameDate().getLastNumGame())
            val fragment = GameFragment()
            fragment.arguments = bundle

            findNavController().navigate(R.id.action_MenuFragment_to_gameFragment, bundle)



        }

        binding.howToPlay.setOnClickListener {
            findNavController().navigate(R.id.action_MenuToHowToPlay)
        }

        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_settingsFragment)
        }

        binding.prevGames.setOnClickListener {
            val simpleDate = SimpleDateFormat("dd/M/yyyy")

            // Build constraints.
            val constraintsBuilder =
                CalendarConstraints.Builder()
                    .setStart(simpleDate.parse("18/9/2022").time)
                    .setEnd(Date().time)
                    .build()

            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setCalendarConstraints(constraintsBuilder)
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                val numGame = gameDate().getNumGame(it).toInt()
                Log.d("CakesTwix-Debug", gameDate().getNumGame(it))
                if(numGame < 0){
                    Toast.makeText(this@MenuFragment.context, getString(R.string.negative_number), Toast.LENGTH_LONG).show()
                } else if (numGame > gameDate().getLastNumGame().toInt()){
                    Toast.makeText(this@MenuFragment.context, getString(R.string.no_game_yet), Toast.LENGTH_LONG).show()
                } else {
                    // All ok, send game id
                    val bundle = Bundle()
                    bundle.putString("num", numGame.toString())
                    val fragment = GameFragment()
                    fragment.arguments = bundle

                    findNavController().navigate(R.id.action_MenuFragment_to_gameFragment, bundle)
                }
            }

            datePicker.show(requireActivity().supportFragmentManager, "DatePicker");
            // Log.d("CakesTwix-Debug", gameData().getNumGame(datePicker.))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}