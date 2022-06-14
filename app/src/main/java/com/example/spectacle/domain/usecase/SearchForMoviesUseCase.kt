package com.example.spectacle.domain.usecase

import android.net.Uri
import com.example.spectacle.data.repository.MoviesRepository
import com.example.spectacle.data.repository.MoviesRepositoryImpl

class SearchForMovieUseCase(private val moviesRepository: MoviesRepository = MoviesRepositoryImpl()) {
    fun executeSearch(movieSearched: Uri) = moviesRepository.searchForMovie(movieSearched)
}