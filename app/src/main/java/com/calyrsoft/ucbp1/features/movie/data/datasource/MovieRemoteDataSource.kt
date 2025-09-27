package com.calyrsoft.ucbp1.features.movie.data.datasource

import android.util.Log
import com.calyrsoft.ucbp1.features.movie.data.api.MovieService
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import retrofit2.HttpException
import java.io.IOException

class MovieRemoteDataSource(
    private val movieService: MovieService,
    private val apiKey: String
) {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        return try {
            val response = movieService.fetchPopularMovies(apiKey = apiKey)
            if (response.isSuccessful) {
                val moviePage = response.body()
                if (!moviePage?.results.isNullOrEmpty()) {
                    val movies = moviePage!!.results.map { dto ->
                        MovieModel(
                            id = dto.title.hashCode(),
                            title = dto.title,
                            pathUrl = "https://image.tmdb.org/t/p/w185" + (dto.pathUrl ?: ""),
                            isLiked = false
                        )
                    }
                    Result.success(movies)
                } else {
                    // fallback: datos de prueba
                    Result.success(generateTestMovies())
                }
            } else {
                Result.success(generateTestMovies()) // fallback
            }
        } catch (e: Exception) {
            Result.success(generateTestMovies()) // fallback
        }
    }

    private fun generateTestMovies(): List<MovieModel> {
        return listOf(
            MovieModel(1, "Movie 1", "https://via.placeholder.com/185x278.png?text=Movie+1"),
            MovieModel(2, "Movie 2", "https://via.placeholder.com/185x278.png?text=Movie+2"),
            MovieModel(3, "Movie 3", "https://via.placeholder.com/185x278.png?text=Movie+3"),
            MovieModel(4, "Movie 4", "https://via.placeholder.com/185x278.png?text=Movie+4")
        )
    }
}

