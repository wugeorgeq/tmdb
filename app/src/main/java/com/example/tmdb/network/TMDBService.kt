package com.example.tmdb.network

import com.example.tmdb.TmdbApiKey
import javax.inject.Inject

class TMDBService @Inject constructor(
  @TmdbApiKey val apikey: String
)
