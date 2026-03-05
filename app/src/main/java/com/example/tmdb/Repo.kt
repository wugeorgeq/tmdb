package com.example.tmdb

interface Repo {
  fun name(): String
}

class RealRepo : Repo {
  override fun name() = "George"
}
