package com.example.myapplication.features.movies

import retrofit2.Call
import retrofit2.http.GET

internal interface MoviesApi {

    companion object{
     private const val PARAM_MOVIE_ID = "movieId"
     private const val MOVIES = "movies.json"
     private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET
    fun movies() : Call<List<MovieEntity>>
    
}