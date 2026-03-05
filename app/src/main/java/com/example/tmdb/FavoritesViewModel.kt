package com.example.tmdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class FavoritesViewModel(
  private val repo: Repo
) : ViewModel() {

  fun getName() = repo.name()

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val repo = (this[APPLICATION_KEY] as TmdbApplication).repo
        FavoritesViewModel(repo)
      }
    }
  }
}
