package com.hollow.imbdfilmes.Api

import com.hollow.imbdfilmes.data.MovieSearchResponse
import com.hollow.imbdfilmes.data.MovieDetail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Chave da API fornecida pelo usuário
const val API_KEY = "5b0ca64d"
const val BASE_URL = "https://www.omdbapi.com/"

interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieSearchResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("plot") plot: String = "full", // Para obter a sinopse completa
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetail
}

object RetrofitClient {
    val movieApi: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}
