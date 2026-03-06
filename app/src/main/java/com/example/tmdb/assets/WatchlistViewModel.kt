package com.example.tmdb.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// The Data Model
data class AssetState(
  val id: String,
  val symbol: String,
  val price: Double,
  val trend: Int // -1: Down, 0: Neutral, 1: Up
)

@HiltViewModel
class WatchlistViewModel @Inject constructor() : ViewModel() {
  private val _assets = MutableStateFlow<List<AssetState>>(
    (1..20).map { i ->
      AssetState("$i", "TICK$i", 100.0 + i, 0)
    }
  )
  val assets = _assets.asStateFlow()

  init {
    viewModelScope.launch {
      while (true) {
        delay(1500) // High frequency updates
        updateRandomAsset()
      }
    }
  }

  private fun updateRandomAsset() {
    val currentList = _assets.value.toMutableList()
    val randomIndex = (0 until currentList.size).random()
    val oldAsset = currentList[randomIndex]

    // Randomly nudge price up or down
    val change = if ((0..1).random() == 1) 0.5 else -0.5
    val newTrend = if (change > 0) 1 else -1

    currentList[randomIndex] = oldAsset.copy(
      price = oldAsset.price + change,
      trend = newTrend
    )
    _assets.value = currentList
  }
}