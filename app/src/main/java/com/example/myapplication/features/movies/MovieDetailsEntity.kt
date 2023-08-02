package com.example.myapplication.features.movies

data class MovieDetailsEntity(
    private val id: Int,
    private val title: String,
    private val poster: String,
    private val summery: String,
    private val cast: String,
    private val director: String,
    private val year: Int,
    private val trailer: String
) {
    companion object {
        val empty = MovieDetailsEntity(0, "", "", "", "", "", 0, "")
    }

    fun toMovieDetails() =
        MovieDetailsEntity(id, title, poster, summery, cast, director, year, trailer)
}