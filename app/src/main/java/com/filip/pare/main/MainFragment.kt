package com.filip.pare.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.filip.pare.R
import com.filip.pare.databinding.FragmentMainBinding
import com.filip.pare.dice.Dice
import com.filip.pare.dice.DiceAdapter
import com.filip.pare.player.Player
import com.filip.pare.player.PlayerAdapter
import com.filip.pare.player.Players
import com.filip.pare.setup.SetupPauseFragment
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private val model by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    fun dataChanged(playerStart: Int, playerCount: Int, diceCount: Int) {
        binding?.apply {
            rvPlayerScores.adapter?.notifyItemRangeChanged(playerStart, playerCount)
            rvDice.adapter?.notifyItemRangeChanged(0, diceCount)
            btnRollPlayer.text = getString(R.string.roll_button_player_text, model.players.currentPlayer)
        }
    }

    private fun getPlayerData(savedInstanceState: Bundle?): Players {
        if (savedInstanceState !== null)
            return model.players

        val sharedPrefs = requireActivity().getSharedPreferences("main", Context.MODE_PRIVATE)

        val playerArr = ArrayList<Player>()
        for (player in 1..sharedPrefs.getInt("player_count", 1))
            playerArr.add(Player())
        val players = Players(playerArr)

        val dice = ArrayList<Dice>()
        for (die in 1..sharedPrefs.getInt("dice_count", 1))
            dice.add(Dice())
        Player.dice = dice

        return players
    }

    fun setClickableButtons(clickable: Boolean) {
        binding?.apply {
            for (button in arrayOf(btnRollPlayer, btnRoll, btnReset)) {
                button.isClickable = clickable
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fun clickButton(buttonId: Int) {
                    val button = requireActivity().findViewById<Button>(buttonId)
                    button.performClick()
                }

                val setupBack = requireActivity().supportFragmentManager.findFragmentByTag("back_fragment")
                val setupWinner = requireActivity().supportFragmentManager.findFragmentByTag("winner_fragment")

                if (setupBack !== null)
                    return clickButton(R.id.btnContinue)
                else if (setupWinner !== null)
                    return clickButton(R.id.btnReSetup)

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mainContainer, SetupPauseFragment(), "back_fragment")
                    .commit()
            }
        })

        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            rvPlayerScores.layoutManager = GridLayoutManager(requireContext(), 1)
            rvDice.layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW).apply {
                justifyContent = JustifyContent.CENTER
            }

            model.players = getPlayerData(savedInstanceState)
            btnRollPlayer.text = getString(R.string.roll_button_player_text, model.players.currentPlayer)

            rvPlayerScores.adapter = PlayerAdapter(model.players.players)
            rvDice.adapter = DiceAdapter(Player.dice)

            btnRollPlayer.setOnClickListener {
                val currentPlayer = model.players.currentPlayer - 1
                model.players.rollDice()
                dataChanged(currentPlayer,1, Player.dice.size)
            }

            btnRoll.setOnClickListener {
                val currentPlayer = model.players.currentPlayer - 1
                model.players.rollDice(model.players.players.size)
                dataChanged(currentPlayer, model.players.players.size, Player.dice.size)
            }

            btnReset.setOnClickListener {
                model.winner = model.players.endGame(getString(R.string.draw_text), getString(R.string.winner_text))
                if (model.winner !== null) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .add(R.id.mainContainer, SetupPauseFragment(), "winner_fragment")
                        .commit()
                }
            }
        }
    }
}