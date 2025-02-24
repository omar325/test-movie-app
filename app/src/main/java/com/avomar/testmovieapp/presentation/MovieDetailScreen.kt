package com.avomar.testmovieapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avomar.testmovieapp.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Composable
fun MovieDetailScreen(
    viewModel: MovieListViewModel,
    movieId: Int,
    onBackClick: () -> Unit
) {
    MovieDetailScreen(
        state = viewModel.state.collectAsState().value,
        movieId = movieId,
        onBackClick
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailScreen(
    state: MovieListState,
    movieId: Int,
    onBackClick: () -> Unit = {}
) = with(state) {
    movies?.find { it.id == movieId }?.let { movie ->
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = movie.posterFullPath,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    loading = placeholder(R.drawable.ic_launcher_foreground),
                    failure = placeholder(R.drawable.ic_launcher_foreground)
                )
                IconButton(
                    modifier = Modifier.padding(20.dp),
                    onClick = { onBackClick() }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "", tint = Color.White)
                }
                Text(
                    text = movie.title,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row {
                    GlideImage(
                        modifier = Modifier
                            .size(110.dp, 180.dp)
                            .padding(20.dp),
                        model = movie.posterFullPath,
                        contentDescription = "",
                        loading = placeholder(R.drawable.ic_launcher_foreground),
                        failure = placeholder(R.drawable.ic_launcher_foreground)
                    )
                    Column(modifier = Modifier
                        .weight(1f)
                        .height(180.dp)
                        .padding(20.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Genres: ${movie.genreIds}",
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                        )
                        Text(
                            text = "Release date: ${movie.releaseDate}"
                        )
                    }
                }
                Text(
                    text = movie.overview
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(state = MovieListState(loading = false, movies = listOf(
        UiMovie(0, "Title", "https://m.media-amazon.com/images/I/A1PaCX4oXjL.jpg", listOf(50, 20), "", ""),
    )), 0)
}