package com.filip.pare.main

import androidx.lifecycle.ViewModel
import com.filip.pare.player.Players

class MainViewModel : ViewModel() {
    lateinit var players: Players
    var winner: String? = null
}