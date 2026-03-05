package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tmdb.ui.FavoritesScreen
import com.example.tmdb.ui.theme.TMDBTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TMDBTheme {
        val favoritesViewModel: FavoritesViewModel by viewModels { FavoritesViewModel.Factory }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          FavoritesScreen(
            name = favoritesViewModel.getName(),
            modifier = Modifier
              .padding(innerPadding)
              .fillMaxSize()
          )
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TMDBTheme {
    Greeting("Android")
  }
}