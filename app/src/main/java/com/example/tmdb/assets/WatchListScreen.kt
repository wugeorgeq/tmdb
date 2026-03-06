package com.example.tmdb.assets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun WatchListScreen(
  viewModel: WatchlistViewModel,
  modifier: Modifier,
) {
  val assets = viewModel.assets.collectAsState().value
  WatchListContents(assets, modifier)
}

@Composable
fun WatchListContents(
  assets: List<AssetState>,
  modifier: Modifier,
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier
      .padding(start = 16.dp, end = 16.dp),
  ) {
    items(
      items = assets,
      key = { it.id },
    ) { asset ->
      AssetRow(
        assetState = asset,
        modifier = Modifier
      )
    }
  }
}

@Composable
fun AssetRow(assetState: AssetState, modifier: Modifier) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    modifier = modifier
      .fillMaxWidth()
  ) {
    SideEffect { println("Recomposing ${assetState.symbol}") }
    Text(assetState.symbol)
    PriceText(price = assetState.price, trend = assetState.trend)
  }
}

@Composable
fun PriceText(price: Double, trend: Int) {
  SideEffect { println("Recomposing price") }
  var color by remember { mutableStateOf(Color.Black) }
  LaunchedEffect(price) {
    color = if (trend < 0) Color.Red else Color.Green
    delay(1000)
    color = Color.Black
  }

  val animatedColor = animateColorAsState(
    targetValue = color,
    animationSpec = tween(durationMillis = 600),
  )

  SideEffect { println("Recomposing with color $animatedColor") }
  Text(
    //formatting later
    text = "$price",
    color = animatedColor.value,
  )
}
