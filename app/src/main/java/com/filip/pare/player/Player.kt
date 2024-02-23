package com.filip.pare.player

import com.filip.pare.dice.Dice

class Player {
    companion object {
        lateinit var dice: ArrayList<Dice>
    }

    var lastRoll: Int = 0
    var totalScore: Int = 0

    fun rollDice() {
        lastRoll = 0
        for (die in dice)
            lastRoll += die.roll()

        totalScore += lastRoll
    }

    fun reset() {
        lastRoll = 0
        totalScore = 0
    }
}

class Players(val players: ArrayList<Player>) {
    var currentPlayer = 1

    fun rollDice(end: Int = currentPlayer) {
        for (player in currentPlayer - 1..<end) {
            players[player].rollDice()
            currentPlayer++
        }

        if (currentPlayer > players.size)
            currentPlayer = 1
    }

    fun reset() {
        for (player in players)
            player.reset()
    }

    fun endGame(): String? {
        var max = 0
        val winners = ArrayList<ArrayList<Int>>()

        winners.add(ArrayList())
        winners[0].add(1)

        for (player in 1..<players.size) {
            if (players[player].totalScore >= players[winners[max][0] - 1].totalScore) {
                if (players[player].totalScore > players[winners[max][0] - 1].totalScore) {
                    max++
                    winners.add(ArrayList())
                }
                winners[max].add(player + 1)
            }
        }

        if (players[winners[max][0] - 1].totalScore == 0)
            return null

        val winner = when {
            winners[max].size > 1 -> "It's a tie between players ${winners[max].joinToString(", ")}!"
            else -> "Player ${winners[max][0]} won with a score of ${players[winners[max][0] - 1].totalScore}!"
        }

        currentPlayer = 1
        return winner
    }
}