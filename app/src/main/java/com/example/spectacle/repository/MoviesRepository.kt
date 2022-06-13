package com.example.spectacle.repository

import android.net.Uri
import com.example.spectacle.domain.Category
import com.example.spectacle.domain.Movie
import io.reactivex.Single

interface MoviesRepository {
    fun getPopularMovies(): Single<List<Movie>>
    fun getAllGenres(): Single<List<Category>>
    fun getMoviesByCategory(categoryId: String): Single<List<Movie>>
    fun searchForMovie(movieSearched: Uri): Single<List<Movie>>
//    fun getRateWatchlist(): Single<List<AccountStates>>
}