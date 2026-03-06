package com.example.tmdb.network

import com.example.tmdb.TmdbApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(
    @TmdbApiKey apiKey: String
  ): OkHttpClient {
    val logging = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
      .addInterceptor(logging)
      .addInterceptor { chain ->
        val original = chain.request()
        val url = original.url.newBuilder()
          .addQueryParameter("api_key", apiKey) // TMDB expects 'api_key'
          .build()

        val request = original.newBuilder()
          .url(url)
          .build()

        chain.proceed(request)
      }
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideTMDBService(retrofit: Retrofit): TMDBService {
    return retrofit.create(TMDBService::class.java)
  }
}
