package com.example.tmdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.domain.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
  private val repo: Repo
) : ViewModel() {

  private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
  val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

  init {
    loadMovies()
  }

  private fun loadMovies() {
    viewModelScope.launch {
      try {
        val movies = repo.getMovies()
        _uiState.value = FavoritesUiState.Success(movies)
      } catch (e: Exception) {
        _uiState.value = FavoritesUiState.Error(e.message ?: "Unknown error")
      }
    }
  }

  fun getName() = repo.name()
}

sealed class FavoritesUiState {
  object Loading : FavoritesUiState()
  data class Success(val data: Any) : FavoritesUiState()
  data class Error(val message: String) : FavoritesUiState()
}
