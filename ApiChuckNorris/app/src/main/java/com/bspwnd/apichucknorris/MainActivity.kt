package com.bspwnd.apichucknorris

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bspwnd.apichucknorris.adapter.JokeAdapter
import com.bspwnd.apichucknorris.databinding.ActivityMainBinding
import com.bspwnd.apichucknorris.databinding.InfoJokeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jokeViewModel: JokeViewModel
    private lateinit var layoutBinding: InfoJokeBinding
    var posix = 0

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
        jokeViewModel.jokesFromCategories(cat)
        jokeViewModel.mutableJoke.observe(this, Observer {
            Log.d("** mutableJoke", jokeViewModel.mutableJoke.value.toString())
            initAlertDialog(jokeViewModel.mutableJoke)
        })
    }

    fun initAlertDialog(mutablejoke: MutableLiveData<Joke>){
        layoutBinding = InfoJokeBinding.inflate(LayoutInflater.from(this))
        val builder = AlertDialog.Builder(this)
        builder.setView(layoutBinding.root)
        val dialog = builder.create()

        layoutBinding.infoCategory.text = "Category: " + mutablejoke.value?.categories.toString()
        layoutBinding.infoCreated.text = "Created at: " + mutablejoke.value?.createdAt.toString()
        layoutBinding.infoValue.text = "Value: " + mutablejoke.value?.value.toString()


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