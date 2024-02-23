package com.filip.pare.dice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filip.pare.databinding.ItemDiceBinding

class DiceAdapter (
    private val dice: ArrayList<Dice>
) : RecyclerView.Adapter<DiceAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemDiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dice.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(dice[position])
    }

    inner class PlayerViewHolder(private val binding: ItemDiceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(die: Dice) {
            binding.dice.setImageResource(die.image)
        }
    }
}