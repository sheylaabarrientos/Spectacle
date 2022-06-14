package com.example.spectacle.domain.usecase

import com.example.spectacle.data.repository.FavoriteMoviesRepositoryImpl
import com.example.spectacle.domain.Movie

class FavoriteMoviesUseCase(private val favoriteMoviesRepository: FavoriteMoviesRepositoryImpl = FavoriteMoviesRepositoryImpl()) {

    fun getFavoriteMovies() = favoriteMoviesRepository.getFavoriteMovies()
    fun addToFavoriteMovie(movie: Movie) = favoriteMoviesRepository.addToFavoriteMovie(movie)
    fun removeFavoriteMovie(movie: Movie) = favoriteMoviesRepository.removeFavoriteMovie(movie)
}