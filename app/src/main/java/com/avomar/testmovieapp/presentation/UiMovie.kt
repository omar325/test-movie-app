package com.avomar.testmovieapp.presentation

import com.avomar.testmovieapp.domain.Movie

data class UiMovie(
    val id: Int,
    val title: String,
    val posterFullPath: String,
    val genreIds: List<Int>,
    val overview: String,
    val releaseDate: String,
)

fun Movie.toUiMovie(
    basePosterPath: String
) = UiMovie(
    id, title, basePosterPath + (posterPath?:""), genreIds, overview, releaseDate
)
