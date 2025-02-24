package com.avomar.testmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avomar.testmovieapp.presentation.theme.TestMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestMovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost()
                }
            }
        }
    }

    @Composable
    fun AppNavHost(
        navController: NavHostController = rememberNavController(),
        startDestination: String = "movieList"
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable("movieList") {
                MovieListScreen(
                    viewModel = hiltViewModel(),
                    onPosterClick = { movieId ->
                        navController.navigate(route = "movieDetail/$movieId")
                    }
                )
            }
            composable("movieDetail/{movieId}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")?:"0"
                MovieDetailScreen(
                    viewModel = hiltViewModel(),
                    movieId = movieId.toInt(),
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
