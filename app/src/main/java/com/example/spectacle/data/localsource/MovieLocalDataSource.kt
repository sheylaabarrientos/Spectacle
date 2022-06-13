package com.example.spectacle.data.localsource

import com.example.spectacle.data.model.movies.MovieResponse
import com.example.spectacle.data.model.user.UserResponse
import io.reactivex.Single

interface MovieLocalDataSource {
    fun addToFavoriteMovie(movie: MovieResponse): Single<List<MovieResponse>>
    fun removeFavoriteMovie(movie: MovieResponse): Single<List<MovieResponse>>
    fun getFavoriteMovies(): Single<List<MovieResponse>>

    fun addToWatchedList(movie: MovieResponse): Single<List<MovieResponse>>
    fun removedWatchedList(movie: MovieResponse): Single<List<MovieResponse>>
    fun getWatchedMovies(): Single<List<MovieResponse>>

    fun addToProfileList(userResponse: UserResponse): Single<List<UserResponse>>
    fun removedProfiledList(userResponse: UserResponse): Single<List<UserResponse>>
    fun getProfileList(): Single<List<UserResponse>>
}
