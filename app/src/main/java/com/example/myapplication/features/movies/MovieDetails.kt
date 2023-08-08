package com.example.myapplication.features.movies

data class MovieDetails(
    val id: Int,
    val title: String,
    val poster: String,
    val summery: String,
    val cast: String,
    val director: String,
    val year: Int,
    val trailer: String
) {
    companion object {
        val empty = MovieDetails(
            0,"","","","","",0,""
        )
    }
}