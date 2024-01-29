package com.knopkapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MoodAdapter(private val moods: List<String>, private val onClick: (String) -> Unit) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mood, parent, false) as TextView
        return MoodViewHolder(view) { position ->
            notifyItemChanged(selectedPosition)
            selectedPosition = position
            notifyItemChanged(selectedPosition)
            onClick(moods[position])
        }
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(moods[position], selectedPosition == position)
    }

    override fun getItemCount() = moods.size

    class MoodViewHolder(private val textView: TextView, val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(textView) {
        fun bind(mood: String, isSelected: Boolean) {
            textView.text = mood
            textView.setOnClickListener { onClick(adapterPosition) }

            textView.setBackgroundResource(
                if (isSelected) R.drawable.mood_true
                else R.drawable.mood_false
            )

            val textColorId = if (isSelected) R.color.white else R.color.green
            textView.setTextColor(ContextCompat.getColor(textView.context, textColorId))
        }
    }
}