package com.example.myapplication.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.core.exception.Failure
import com.example.myapplication.core.extension.failure
import com.example.myapplication.core.extension.invisible
import com.example.myapplication.core.extension.observe
import com.example.myapplication.core.extension.visible
import com.example.myapplication.core.platform.BaseFragment
import com.example.myapplication.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    private val moviesViewModel: MovieViewModel by viewModels()

    private var _binding: FragmentMoviesBinding? = null;
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(moviesViewModel) {
            observe(movies, ::renderMovieList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeView(){
        binding.movieList.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.movieList.adapter = moviesAdapter
    }

    private fun renderMovieList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is MovieFailure.ListNotFound -> renderFailure(R.string.failure_movies_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun loadMovieList() {
        binding.emptyView.invisible()
        binding.movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderFailure(@StringRes message: Int) {
        binding.movieList.invisible()
        binding.emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMovieList)
    }
}