package com.bspwnd.apichucknorris.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bspwnd.apichucknorris.R

class JokeAdapter(private val jokeList:List<String>, private val onClickListener:(cat: String) -> Unit) : RecyclerView.Adapter<JokeViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val context = parent.context
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_recycler, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val item = jokeList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }
}