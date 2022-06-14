package com.example.spectacle.domain.usecase

import com.example.spectacle.data.repository.MoviesRepositoryImpl

class GetCategoriesUseCase(private val repository: MoviesRepositoryImpl = MoviesRepositoryImpl()) {
    fun executeGenres() = repository.getAllGenres()
}