package com.example.tmdb.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
  @Binds
  abstract fun bindRepo(realRepo: RealRepo): Repo
}
