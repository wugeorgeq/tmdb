package com.example.tmdb

import android.app.Application

class TmdbApplication: Application() {
  val repo = RealRepo()
}