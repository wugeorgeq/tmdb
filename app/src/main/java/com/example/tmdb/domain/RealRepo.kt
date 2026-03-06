package com.example.tmdb.domain

import com.example.tmdb.network.TMDBService
import javax.inject.Inject

class RealRepo @Inject constructor(
  tmdbService: TMDBService
) : Repo {
  override fun name() = "George"
}
