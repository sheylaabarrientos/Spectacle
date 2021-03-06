package com.example.spectacle.data.remotesource

import android.net.Uri
import com.example.spectacle.data.model.categories.ResponseCategories
import com.example.spectacle.data.model.movies.MovieInfoResponse
import com.example.spectacle.data.model.movies.ResponseMovies
import com.example.spectacle.data.model.user.ResponseUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteSource {
    @GET("movie/popular")
    fun getPopularMovies(): Single<ResponseMovies>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<MovieInfoResponse>

    @GET("search/movie")
    fun searchForMovie(@Query("query") movieSearched: Uri): Single<ResponseMovies>

    @GET("genre/movie/list")
    fun getAllGenres(): Single<ResponseCategories>

    @GET("discover/movie")
    fun getMoviesByCategory(@Query("with_genres", encoded = true) categoriesId: String): Single<ResponseMovies>

    @GET("movie/{movie_id}/account_states")
    fun getRateWatchlist(): Single<ResponseUser>
}
