package com.avomar.testmovieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avomar.testmovieapp.data.DataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val dataSource: DataSource
): ViewModel() {
    private var _state = MutableStateFlow(MovieListState(loading = true))
    val state = _state.asStateFlow()

    init {
        refreshList()
    }

    fun refreshList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                var movies: List<UiMovie>? = null
                var error: String? = null
                try {
                    movies = dataSource.getMovies()
                } catch (e: Exception) {
                    error = "No data available, please try again"
                }
                it.copy(
                    loading = false,
                    movies = movies,
                    filteredMovies = movies,
                    error = error
                )
            }
        }
    }

    fun searchMovie(query: String) {
        _state.update {
            it.copy(
                filteredMovies = _state.value.movies?.filter { it.title.contains(query, ignoreCase = true) },
                searchQuery = query
            )
        }
    }
}

data class MovieListState(
    val loading: Boolean = true,
    val movies: List<UiMovie>? = null,
    val filteredMovies: List<UiMovie>? = null,
    val error: String? = null,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
)