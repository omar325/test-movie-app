package com.avomar.testmovieapp.domain

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
)
