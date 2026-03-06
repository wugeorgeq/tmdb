package com.example.tmdb.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
  /**
   * Discovers movies by popularity.
   * The Interceptor automatically adds the ?api_key=... parameter.
   */
  @GET("discover/movie")
  suspend fun getMovies(
    @Query("sort_by") sortBy: String = "popularity.desc",
    @Query("page") page: Int = 1
  ): MovieResponse
}
