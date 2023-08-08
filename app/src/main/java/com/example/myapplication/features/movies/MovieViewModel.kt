package com.example.myapplication.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.interactor.UseCase
import com.example.myapplication.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(private val getMovies: GetMovies) : BaseViewModel(){
    private val _movies: MutableLiveData<List<MovieView>> = MutableLiveData()
    val movies: LiveData<List<MovieView>> = _movies

    fun loadMovies() = getMovies(UseCase.None(), viewModelScope) { it.fold(::handleFailure, ::handleMovieList)}

    private  fun handleMovieList(movies: List<Movie>){
        _movies.value = movies.map { MovieView(it.id, it.poster) }
    }
}