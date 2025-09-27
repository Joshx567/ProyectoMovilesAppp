package com.calyrsoft.ucbp1.features.movie.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val isLiked: Boolean = false
)