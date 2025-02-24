package com.avomar.testmovieapp.framework.remote

import com.avomar.testmovieapp.domain.Movie

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>
)
