package com.example.spectacle.data.mappers

import com.example.spectacle.data.model.movies.MovieResponse
import com.example.spectacle.domain.Movie

class MovieMapper {
    fun map(movieResponseList: List<MovieResponse>): List<Movie> {
        val movies = mutableListOf<Movie>()
        movieResponseList.forEach {
            val movie = Movie(
                imgHome = it.imgHome,
                id = it.id,
                title = it.title,
                rating = it.rating,
                categoryIds = it.genreIds,
                inFavoriteList = it.isFavorite
            )
            movies.add(movie)
        }
        return movies
    }
}
