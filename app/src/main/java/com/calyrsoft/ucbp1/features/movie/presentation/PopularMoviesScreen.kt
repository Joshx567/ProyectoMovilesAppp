package com.calyrsoft.ucbp1.features.movie.presentation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import com.calyrsoft.ucbp1.navigation.Screen

@Composable
fun PopularMoviesScreen(
    navController: NavController,
    popularMoviesViewModel: PopularMoviesViewModel = koinViewModel()
) {
    val state = popularMoviesViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        popularMoviesViewModel.fetchPopularMovies()
    }

    when (val s = state.value) {
        is PopularMoviesViewModel.UiState.Error -> {
            Text(s.message)
        }
        is PopularMoviesViewModel.UiState.Loading -> {
            CircularProgressIndicator()
        }
        is PopularMoviesViewModel.UiState.Success -> {
            PopularMoviesView(
                movies = s.movies,
                onLikeClick = { movie ->
                    popularMoviesViewModel.toggleLike(movie)
                }
            )
        }
    }

    // Botón para ir a DollarScreen
    Button(onClick = { navController.navigate(Screen.Dollar.route) }) {
        Text("Ir a Dollar")
    }
}
