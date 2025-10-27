package com.hollow.imbdfilmes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hollow.imbdfilmes.Api.RetrofitClient
import com.hollow.imbdfilmes.data.Movie
import com.hollow.imbdfilmes.data.MovieDetail
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var movieDetail by mutableStateOf<MovieDetail?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private var searchJob: Job? = null

    fun updateSearchQuery(newQuery: String) {
        searchQuery = newQuery
        // Inicia a busca automaticamente com um pequeno delay (debounce)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500) // Debounce de 500ms
            if (newQuery.isNotBlank()) {
                searchMovies(newQuery)
            } else {
                movies = emptyList()
            }
        }
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitClient.movieApi.searchMovies(query)
                if (response.response == "True") {
                    movies = response.search ?: emptyList()
                } else {
                    movies = emptyList()
                    errorMessage = response.error ?: "Nenhum filme encontrado."
                }
            } catch (e: Exception) {
                errorMessage = "Erro de conexão: ${e.message}"
                movies = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun getMovieDetails(imdbId: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            movieDetail = null
            try {
                val detail = RetrofitClient.movieApi.getMovieDetails(imdbId)
                if (detail.response == "True") {
                    movieDetail = detail
                } else {
                    errorMessage = detail.error ?: "Detalhes do filme não encontrados."
                }
            } catch (e: Exception) {
                errorMessage = "Erro ao buscar detalhes: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearMovieDetails() {
        movieDetail = null
    }
}
