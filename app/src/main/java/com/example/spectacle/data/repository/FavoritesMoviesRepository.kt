package com.example.spectacle.data.repository

import com.example.spectacle.domain.Movie
import io.reactivex.Single

interface FavoriteMoviesRepository {
    fun addToFavoriteMovie(movie: Movie): Single<List<Movie>>
    fun removeFavoriteMovie(movie: Movie): Single<List<Movie>>
    fun getFavoriteMovies(): Single<List<Movie>>
}