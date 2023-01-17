package com.bspwnd.apichucknorris

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeApiService {
/*Al abrir la app, tiene que hacer una peticion y que devuelva todas las categorias en un listado que pasaremos al RV
CUANDO PULSES EN LA CATEGORIA TE MUESTRE UNA BROMA DE ESA CATEGORIA
ADEMAS DE LAS CATEGORIAS QUE TENGA UN BOTON CON UNA BROMA ALEATORIA*/

    @GET("/jokes/categories/")
    suspend fun getCategoryList(): Response<List<JokeCategory>>
    //Call<List<String>> me permite usar .enqueue, Response<List<String>> no

    @GET("/jokes/random?category={category}")
    suspend fun geytRandomCategoryQuote(@Path("category") category: String): Call<String>

    @GET("/jokes/random/")
    suspend fun fullRandomQuote()

//Utilizariamos la anotacion @body si le queremos pasar un parametro como el id
}
