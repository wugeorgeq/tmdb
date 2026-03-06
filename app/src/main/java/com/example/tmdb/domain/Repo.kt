package com.example.tmdb.domain

import com.example.tmdb.network.MovieResponse

interface Repo {
  fun name(): String

  suspend fun getMovies(): MovieResponse
}
