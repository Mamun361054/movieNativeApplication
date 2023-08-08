package com.example.myapplication.features.movies
import com.example.myapplication.core.interactor.UseCase
import javax.inject.Inject

class GetMovies
@Inject constructor(private  val moviesRepository: MovieRepository) : UseCase<List<Movie>, UseCase.None>(){
    override suspend fun run(params: None) = moviesRepository.movies()
}