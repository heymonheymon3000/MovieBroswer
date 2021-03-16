package com.example.moviebrowser.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String,
    val adult: Boolean,
    val releaseDate: String,
    val tagline: String,)
