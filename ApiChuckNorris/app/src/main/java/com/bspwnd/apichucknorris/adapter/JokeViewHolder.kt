package com.bspwnd.apichucknorris.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bspwnd.apichucknorris.databinding.ItemRecyclerBinding

class JokeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemRecyclerBinding.bind(view)

    fun render(catModel: String, onClickListener:(cat: String) -> Unit){

        binding.tvCategories.text = catModel
        itemView.setOnClickListener { onClickListener(catModel) }
    }
}