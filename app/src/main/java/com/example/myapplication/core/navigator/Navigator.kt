package com.example.myapplication.core.navigator

import android.content.Context
import android.view.View
import com.example.myapplication.features.movies.MoviesActivity
import java.net.Authenticator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(){

    fun showMain(context: Context){
        showMovies(context)
    }
    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))
    class  Extras(val transitionSharedElement: View)
}