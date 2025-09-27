package com.calyrsoft.ucbp1.features.movie.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


import  com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity

@Dao
interface IMovieDao {
    @Query("SELECT * FROM movies ORDER BY isLiked DESC")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)
}
