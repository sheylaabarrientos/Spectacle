package com.example.spectacle.onclick

import com.example.spectacle.domain.Movie

interface MovieListener {
    fun loadMoviesWithGenre(genreIds: List<Int>)
    fun favoriteListClickedListener(movie: Movie, isChecked: Boolean)
}