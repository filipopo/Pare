package com.filip.pare.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.recyclerview.widget.RecyclerView
import com.filip.pare.databinding.ItemLanguageBinding

class LanguageAdapter (
    private val languages: ArrayList<Language>
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(languages[position])
    }

    inner class LanguageViewHolder(private val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            itemView.setOnClickListener {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.id))
            }

            binding.apply {
                flag.setImageResource(language.flag)
                flag.contentDescription = itemView.context.getString(language.name)
                name.text = itemView.context.getString(language.name)
            }
        }
    }
}