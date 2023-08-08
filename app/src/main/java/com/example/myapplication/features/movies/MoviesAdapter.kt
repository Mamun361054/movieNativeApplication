package com.example.myapplication.features.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.core.extension.loadFromUrl
import com.example.myapplication.core.navigator.Navigator
import com.example.myapplication.databinding.RowMovieBinding
import javax.inject.Inject
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowMovieBinding =
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(rowMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
       holder.bind(collection[position],clickListener)
    }

    override fun getItemCount(): Int = collection.size

    class ViewHolder(private val rowMovieBinding: RowMovieBinding) :
        RecyclerView.ViewHolder(rowMovieBinding.root) {
        fun bind(movieView: MovieView, clickListener: (MovieView, Navigator.Extras) -> Unit) {
            rowMovieBinding.moviePoster.loadFromUrl(movieView.poster)
            itemView.setOnClickListener {
                clickListener(
                    movieView,
                    Navigator.Extras(rowMovieBinding.moviePoster)
                )
            }
        }
    }

}