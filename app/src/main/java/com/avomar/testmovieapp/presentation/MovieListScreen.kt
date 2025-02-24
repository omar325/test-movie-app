package com.avomar.testmovieapp.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun MovieListScreen(
    viewModel:MovieListViewModel
) {
    MovieListScreen(viewModel.state.collectAsState().value)
}

@Composable
fun MovieListScreen(
    state: MovieListState
) = with(state) {
    if (loading) {
        Text(text = "Loading")
        return@with
    }
    error?.let { Text(text = it) }
    movies?.let { Text(text = it.toString()) }
}