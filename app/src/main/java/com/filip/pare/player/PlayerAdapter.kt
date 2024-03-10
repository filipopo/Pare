package com.filip.pare.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filip.pare.R
import com.filip.pare.databinding.ItemScoresBinding

class PlayerAdapter (
    private val players: ArrayList<Player>
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemScoresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    inner class PlayerViewHolder(private val binding: ItemScoresBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.apply {
                playerLast.text = itemView.context.getString(
                    R.string.player_last,
                    adapterPosition + 1,
                    player.lastRoll
                )

                playerTotal.text = itemView.context.getString(
                    R.string.player_total,
                    adapterPosition + 1,
                    player.totalScore
                )

                val padEnd = playerTotal.paint.measureText("a").toInt() * (13 - playerTotal.length())
                playerTotal.setPadding(0, 0, padEnd.coerceAtLeast(0), 0)
            }
        }
    }
}