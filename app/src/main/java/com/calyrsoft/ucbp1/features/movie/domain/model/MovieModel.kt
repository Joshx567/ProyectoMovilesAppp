package com.calyrsoft.ucbp1.features.movie.domain.model

data class MovieModel(
    val id: Int = 0,
    val title: String,
    val pathUrl: String?,
    val isLiked: Boolean = false
)
