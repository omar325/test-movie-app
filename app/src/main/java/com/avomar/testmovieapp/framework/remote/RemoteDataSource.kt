package com.avomar.testmovieapp.framework.remote

import com.avomar.testmovieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.*
import retrofit2.http.GET

interface RemoteDataSource {
    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>

    companion object {
        fun provideInstance(): RemoteDataSource = Retrofit.Builder()
            .addConverterFactory(create())
            .baseUrl("https://api.themoviedb.org/3/")
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }).build())
            .build()
            .create(RemoteDataSource::class.java)
    }
}