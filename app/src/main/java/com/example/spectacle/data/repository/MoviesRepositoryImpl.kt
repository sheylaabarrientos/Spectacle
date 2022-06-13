package com.example.spectacle.data.repository

import android.net.Uri
import com.example.spectacle.data.base.Network
import com.example.spectacle.data.localsource.MovieLocalDataSourceImpl
import com.example.spectacle.data.remotesource.MoviesRemoteSource
import com.example.spectacle.domain.AccountStates
import com.example.spectacle.domain.Category
import com.example.spectacle.domain.Movie
import com.example.spectacle.data.mappers.CategoryMapper
import com.example.spectacle.data.mappers.MovieMapper
import com.example.spectacle.data.mappers.UserMapper
import io.reactivex.Single

class MoviesRepositoryImpl : MoviesRepository {
    private val moviesRemoteSource: MoviesRemoteSource =
        Network.getMoviesRemoteSource()
    private val movieLocalDataSource = MovieLocalDataSourceImpl
    private val movieMapper = MovieMapper()
    private val genreMapper = CategoryMapper()
    private val userMapper = UserMapper()

    override fun getRateWatchlist(): Single<List<AccountStates>> {
        return moviesRemoteSource
            .getRateWatchlist()
            .flatMap { userResponseList ->
                movieLocalDataSource
                    .getProfileList()
                    .map { profileList ->
                        userResponseList.profileResults.forEach { userResponse ->
                            val result = profileList.any { profile ->
                                profile.id == userResponse.id
                            }
                            userResponse.favorite = result
                        }
                        userResponseList.profileResults
                    }

            }
            .map {
                userMapper.map(it)
            }
    }

    override fun getPopularMovies(): Single<List<Movie>> {
        return moviesRemoteSource
            .getPopularMovies()
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

    override fun getAllGenres(): Single<List<Category>> {
        return moviesRemoteSource
            .getAllGenres()
            .map {
                genreMapper.map(it.genres)
            }
    }

    override fun getMoviesByCategory(categoryId: String): Single<List<Movie>> {
        return moviesRemoteSource
            .getMoviesByCategory(categoryId)
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }

    override fun searchForMovie(movieSearched: Uri): Single<List<Movie>> {
        return moviesRemoteSource
            .searchForMovie(movieSearched)
            .flatMap { movieResponseList ->
                movieLocalDataSource
                    .getFavoriteMovies()
                    .map { favoriteMovieList ->
                        movieResponseList.movieResults.forEach { movieResponse ->
                            val result = favoriteMovieList.any { favoriteMovie ->
                                favoriteMovie.id == movieResponse.id
                            }
                            movieResponse.isFavorite = result
                        }
                        movieResponseList.movieResults
                    }
            }
            .map {
                movieMapper.map(it)
            }
    }
}