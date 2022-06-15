package com.example.spectacle.onclick

import com.example.spectacle.domain.Movie

interface MovieListener {
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun onWatchListClickedListener(movie: Movie, isChecked: Boolean)
    fun onWatchedListClickedListener(movie: Movie, isChecked: Boolean)
}