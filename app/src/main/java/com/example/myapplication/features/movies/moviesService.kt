package com.example.myapplication.features.movies

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class moviesService {
    @Inject constructor(retrofit: Retrofit) : MoviesApi {

    }
}