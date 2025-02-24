package com.avomar.testmovieapp.data

import com.avomar.testmovieapp.framework.remote.RemoteDataSource
import com.avomar.testmovieapp.presentation.UiMovie
import com.avomar.testmovieapp.presentation.toUiMovie
import javax.inject.Inject

interface DataSource {
    suspend fun getMovies(): List<UiMovie>

    class Default @Inject constructor(
        private val remoteDataSource: RemoteDataSource
    ): DataSource {
        private var movies: List<UiMovie>? = null

        override suspend fun getMovies(): List<UiMovie> =
            movies ?:
            (remoteDataSource.getPopularMovies().body()?.results?.map { movie ->
                movie.toUiMovie(basePosterPath = "https://image.tmdb.org/t/p/original")
            } ?: emptyList())
                .also { movies = it }
    }
}