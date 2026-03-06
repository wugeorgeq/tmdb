package com.example.tmdb

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
  @Provides
  @TmdbApiKey
  fun provideApiKey(): String = BuildConfig.apikey
}
