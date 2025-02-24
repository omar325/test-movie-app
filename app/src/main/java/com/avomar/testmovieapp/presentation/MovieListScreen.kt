package com.avomar.testmovieapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avomar.testmovieapp.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Composable
fun MovieListScreen(
    viewModel:MovieListViewModel,
    onPosterClick: (Int) -> Unit
) {
    MovieListScreen(
        state = viewModel.state.collectAsState().value,
        onPosterClick = onPosterClick
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListScreen(
    state: MovieListState,
    onPosterClick: (Int) -> Unit = {}
) = with(state) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            return@Column
        }
        error?.let { Text(text = it) }
        movies?.let {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 105.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(it) { movie ->
                    GlideImage(
                        modifier = Modifier
                            .size(110.dp, 180.dp)
                            .clickable {
                                onPosterClick(movie.id)
                            },
                        model = movie.posterFullPath,
                        contentDescription = "",
                        loading = placeholder(R.drawable.ic_launcher_foreground),
                        failure = placeholder(R.drawable.ic_launcher_foreground)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun MovieListScreenPreview() {
    MovieListScreen(state = MovieListState(loading = true))
}
@Preview
@Composable
fun MovieListScreenPreview1() {
    MovieListScreen(state = MovieListState(loading = false, movies = listOf(
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", emptyList(), "", ""),
    )))
}
