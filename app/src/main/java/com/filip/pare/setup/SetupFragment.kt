package com.filip.pare.setup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.filip.pare.R
import com.filip.pare.databinding.FragmentSetupBinding
import com.filip.pare.language.Language
import com.filip.pare.language.LanguageAdapter
import com.filip.pare.main.MainFragment
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class SetupFragment : Fragment() {
    private var binding: FragmentSetupBinding? = null

    private fun isValidInput(): Int {
        binding?.apply {
            if ("${edPlayers.text}".isEmpty() or "${edDice.text}".isEmpty())
                return 1

            val playerCount = "${edPlayers.text}".toInt()
            val diceCount = "${edDice.text}".toInt()

            if (1 > playerCount || playerCount > 99)
                return 2
            else if (1 > diceCount || diceCount > 6)
                return 3
        }

        return -1
    }

    private fun setup() {
        val sharedPrefs = requireActivity().getSharedPreferences("main", Context.MODE_PRIVATE)
        binding?.apply {
            sharedPrefs.edit()
                .putInt("player_count", "${edPlayers.text}".toInt())
                .putInt("dice_count", "${edDice.text}".toInt())
                .apply()
        }

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, MainFragment(), "main_fragment")
            .commit()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSetupBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnSetup.setOnClickListener {
                when (isValidInput()) {
                    1 -> showToast(getString(R.string.incorrect_input))
                    2 -> showToast(getString(R.string.incorrect_player_count))
                    3 -> showToast(getString(R.string.incorrect_dice_count))
                    else -> setup()
                }
            }

            rvLanguages.layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW).apply {
                justifyContent = JustifyContent.CENTER
            }

            val languageArr = arrayListOf(
                Language(
                    "Default",
                    R.string.language_english,
                    R.drawable.usa
                ),
                Language(
                    "sr",
                    R.string.langauge_serbian,
                    R.drawable.serbia
                )
            )

            rvLanguages.adapter = LanguageAdapter(languageArr)
        }
    }
}