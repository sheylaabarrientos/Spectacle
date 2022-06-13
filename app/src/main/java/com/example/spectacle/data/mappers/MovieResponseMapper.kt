package com.example.spectacle.data.mappers

import com.example.spectacle.data.model.movies.MovieResponse
import com.example.spectacle.domain.Movie

class MovieResponseMapper {
    fun map(movie: Movie): MovieResponse {
        return MovieResponse(
            imgHome = movie.imgHome,
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genreIds = movie.categoryIds,
            isFavorite = movie.inWatchList,
            watchedMovie = movie.watchedMovie,
        )
    }
}