package com.calyrsoft.ucbp1.features.movie.data.repository

import com.calyrsoft.ucbp1.features.movie.data.api.MovieService
import com.calyrsoft.ucbp1.features.movie.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private val movieService: MovieService,
    private val movieDao: IMovieDao,
    private val apiKey: String? = null // puede ser null si no hay API Key
) : IMoviesRepository {

    override suspend fun fetchPopularMovies(): Result<List<MovieModel>> = withContext(Dispatchers.IO) {
        try {
            val movies = if (!apiKey.isNullOrEmpty()) {
                // Intentar API
                val response = movieService.fetchPopularMovies(apiKey = apiKey)
                if (response.isSuccessful) {
                    response.body()?.results?.map { dto ->
                        MovieModel(
                            id = dto.title.hashCode(),
                            title = dto.title,
                            pathUrl = dto.pathUrl,
                            isLiked = false
                        )
                    } ?: emptyList()
                } else {
                    getDummyMovies() // si falla API, usar datos locales
                }
            } else {
                getDummyMovies() // no hay API Key
            }

            // Combinar con likes locales
            val localMovies = movieDao.getMovies()
            val combined = movies.map { movie ->
                localMovies.find { it.id == movie.id }?.let {
                    movie.copy(isLiked = it.isLiked)
                } ?: movie
            }

            Result.success(combined)

        } catch (e: Exception) {
            // En caso de error, usar datos locales
            Result.success(getDummyMovies())
        }
    }

    override suspend fun toggleLike(movie: MovieModel) {
        val updated = movie.copy(isLiked = !movie.isLiked)
        movieDao.insertMovie(updated.toEntity())
    }

    // Datos de prueba
    private fun getDummyMovies(): List<MovieModel> {
        return listOf(
            MovieModel(
                id = 1,
                title = "Inception",
                pathUrl = "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_FMjpg_UX1000_.jpg"
            ),
            MovieModel(
                id = 2,
                title = "Interstellar",
                pathUrl = "https://image.tmdb.org/t/p/w185/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg"
            ),
            MovieModel(
                id = 3,
                title = "The Dark Knight",
                pathUrl = "https://image.tmdb.org/t/p/w185/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
            )
        )
    }
}

// Mapper
fun MovieModel.toEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    posterPath = this.pathUrl,
    isLiked = this.isLiked
)
