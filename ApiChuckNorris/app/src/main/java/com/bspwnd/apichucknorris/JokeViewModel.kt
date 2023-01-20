package com.bspwnd.apichucknorris

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeViewModel: ViewModel()  {

    val mutableCategory: MutableLiveData<MutableList<String>> = MutableLiveData()
    var mutableJoke: MutableLiveData<Joke> = MutableLiveData()

    init {
        kotlin.run {
            viewModelScope.launch{
                val api = serviceCreator()
                val response = api.getCategoryList() /**/
                if (response.isSuccessful){
                    val jokes = response.body()
                    mutableCategory.value = jokes as MutableList<String>
                }
            }
        }
    }

    fun jokesFromCategories(cat: String)
    {
        viewModelScope.launch{
            val api = serviceCreator()
            val response = api.getRandomCategoryJoke(cat)
            if (response.isSuccessful){
                response.body()?.let { mutableJoke.value = it }
            }
        }
    }

    private fun serviceCreator(): JokeApiService { //CREAR SERVICIO PARA HACER LAS LLAMADAS
        return getRetrofitInstance().create(JokeApiService::class.java)
    }

    private fun getRetrofitInstance(): Retrofit { //CREAR INSTANCIA RETROFIT
        return Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}



