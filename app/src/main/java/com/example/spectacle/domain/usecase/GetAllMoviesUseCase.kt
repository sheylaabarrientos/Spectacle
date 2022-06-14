package com.example.spectacle.domain.usecase

import com.example.spectacle.data.repository.MoviesRepositoryImpl

class GetAllMoviesUseCase(private val moviesRepository: MoviesRepositoryImpl = MoviesRepositoryImpl()) {
    fun execute() = moviesRepository.getPopularMovies()
}