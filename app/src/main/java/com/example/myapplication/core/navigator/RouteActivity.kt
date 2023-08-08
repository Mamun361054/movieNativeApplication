package com.example.myapplication.core.navigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.features.movies.MoviesActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RouteActivity : AppCompatActivity() {
//    @Inject
//    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.startActivity(MoviesActivity.callingIntent(this))
    }
}