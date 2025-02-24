package com.avomar.testmovieapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    val state = viewModel.state.collectAsState().value

    MovieListScreen(
        state = state,
        onPosterClick = onPosterClick,
        searchMovie = {
            viewModel.searchMovie(it)
        },
        onTryAgainClick = {
            viewModel.refreshList()
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    state: MovieListState,
    onPosterClick: (Int) -> Unit = {},
    searchMovie: (String) -> Unit = {},
    onTryAgainClick: () -> Unit = {},
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
        error?.let {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.data_error))
                Button(onClick = { onTryAgainClick() }) {
                    Text(text = stringResource(id = R.string.try_again))
                }
            }
        }
        filteredMovies?.let {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchMovie(it) },
                onSearch = { },
                active = isSearching,
                onActiveChange = {},
                modifier = Modifier
                    .padding(start = 12.dp, top = 2.dp, end = 12.dp, bottom = 12.dp)
                    .fillMaxWidth(),

                placeholder = { Text("Search") },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                trailingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null
                        )
                }
            ) {}
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
