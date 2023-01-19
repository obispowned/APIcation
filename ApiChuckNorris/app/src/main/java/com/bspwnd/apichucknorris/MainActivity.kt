package com.bspwnd.apichucknorris

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bspwnd.apichucknorris.adapter.JokeAdapter
import com.bspwnd.apichucknorris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jokeViewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        jokeViewModel = ViewModelProvider(this)[JokeViewModel::class.java]
        binding.jokeViewModel = jokeViewModel

        jokeViewModel.mutableCategory.observe(this, Observer{
            initRecyclerView()
        })

        refreshApp()
    }

    private fun initRecyclerView() {
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = JokeAdapter(jokeViewModel.mutableCategory.value as List<String>, {cat -> onItemSelected(cat)})
    }

    fun onItemSelected(cat: String){
        jokeViewModel.mutableJoke.observe(this, Observer {
            Log.d("PROBANDO2", cat)
        })
        initAlertDialog(cat)
    }

    fun initAlertDialog(cat: String){
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.info_joke, null)
        builder.setView(view) //pasamos la vista al builder
        val dialog = builder.create()

        view.findViewById<TextView>(R.id.infoCategory).text = "Category: " + cat

        dialog.show()
    }

    private fun refreshApp(){
        val refresh = binding.refresh
        refresh.setOnRefreshListener{
            initRecyclerView()
            refresh.isRefreshing = false
        }
    }

}