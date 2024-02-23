package com.filip.pare.dice

import com.filip.pare.R
import java.util.Random

class Dice(var image: Int = diceArray[0]) {
    companion object {
        private val randomNumberGenerator = Random()
        val diceArray = intArrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )
    }

    fun roll(): Int {
        val index = randomNumberGenerator.nextInt(6)
        image = diceArray[index]

        return index + 1
    }
}
