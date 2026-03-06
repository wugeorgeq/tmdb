package com.example.tmdb

import androidx.lifecycle.ViewModel
import com.example.tmdb.domain.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
  private val repo: Repo
) : ViewModel() {
  fun getName() = repo.name()
}
