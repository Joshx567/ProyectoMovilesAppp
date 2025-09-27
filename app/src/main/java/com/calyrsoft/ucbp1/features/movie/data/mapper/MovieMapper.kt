package com.calyrsoft.ucbp1.features.movie.data.mapper

import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

// Entity -> Model
fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        id = id,
        title = title,
        pathUrl = posterPath,
        isLiked = isLiked
    )
}

// Model -> Entity
fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = pathUrl,
        isLiked = isLiked
    )
}
