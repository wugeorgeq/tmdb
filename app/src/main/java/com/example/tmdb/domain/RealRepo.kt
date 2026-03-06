package com.example.tmdb.domain

import com.example.tmdb.network.MovieResponse
import com.example.tmdb.network.TMDBService
import javax.inject.Inject

class RealRepo @Inject constructor(
  private val tmdbService: TMDBService
) : Repo {
  override fun name() = "George"

  // TODO error handling and domain modeling
  override suspend fun getMovies(): MovieResponse {
    return tmdbService.getMovies()
  }
}
