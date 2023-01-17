package com.bspwnd.apichucknorris

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeViewModel: ViewModel() {
    init {
        kotlin.run {
            viewModelScope.launch{
                val api = serviceCreator()
                val response = api.getCategoryList() /**/
                Log.d("PROBANDO", response.toString())
                if (response.isSuccessful){
                    val jokes = response.body()
                    if (jokes != null) {
                        for (joke in jokes){
                            Log.d("PROBANDO", joke.toString())
                        }
                    }
                }else{
                    Log.d("PROBANDO", "ERROR: response")
                }
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



