package com.example.tmdb.domain

import javax.inject.Inject

class RealRepo @Inject constructor() : Repo {
  override fun name() = "George"
}
