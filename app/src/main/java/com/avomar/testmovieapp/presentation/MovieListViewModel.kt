package com.avomar.testmovieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avomar.testmovieapp.domain.Movie
import com.avomar.testmovieapp.framework.remote.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): ViewModel() {
    private var _state = MutableStateFlow(MovieListState(loading = true))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                var movies: List<Movie>? = null
                var error: String? = null
                try {
                    movies = remoteDataSource.getPopularMovies().body()?.results
                } catch (e: Exception) {
                    error = e.message
                }
                it.copy(
                    loading = false,
                    movies = movies,
                    error = error
                )
            }
        }
    }
}

data class MovieListState(
    val loading: Boolean = true,
    val movies: List<Movie>? = null,
    val error: String? = null
)