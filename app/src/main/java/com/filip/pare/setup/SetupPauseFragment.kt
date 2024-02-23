package com.filip.pare.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.filip.pare.R
import com.filip.pare.databinding.FragmentSetupPauseBinding
import com.filip.pare.main.MainFragment
import com.filip.pare.main.MainViewModel

class SetupPauseFragment : Fragment() {
    private var binding: FragmentSetupPauseBinding? = null
    private val model by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSetupPauseBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainFragment = requireActivity().supportFragmentManager.findFragmentByTag("main_fragment") as MainFragment
        mainFragment.setClickableButtons(false)

        binding?.apply {
            winner.text = model.winner ?: getString(R.string.back_text)

            btnContinue.setOnClickListener{
                mainFragment.setClickableButtons(true)

                if (model.winner !== null) {
                    model.players.reset()
                    mainFragment.dataChanged(0, model.players.players.size, 0)
                }

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(this@SetupPauseFragment)
                    .commit()
            }

            btnReSetup.setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainer, SetupFragment())
                    .commit()
            }
        }
    }
}