package com.example.tmdb.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tmdb.FavoritesUiState
import com.example.tmdb.FavoritesViewModel

@Composable
fun FavoritesScreen(
  viewModel: FavoritesViewModel,
  modifier: Modifier = Modifier
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val name = viewModel.getName()

  FavoritesContent(
    name = name,
    uiState = uiState,
    modifier = modifier
  )
}

@Composable
fun FavoritesContent(
  name: String,
  uiState: FavoritesUiState,
  modifier: Modifier = Modifier
) {
  println(uiState)
  Column(modifier = modifier) {
    Text(text = "Hello $name")
  }
}