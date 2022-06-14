package com.example.spectacle.domain.usecase

import com.example.spectacle.data.repository.MoviesRepositoryImpl

class GetMoviesByGenreUseCase(private val repository: MoviesRepositoryImpl = MoviesRepositoryImpl()) {
    fun executeMoviesByCategory(categoryId: String) = repository.getMoviesByCategory(categoryId)
}