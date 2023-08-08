package com.example.myapplication.features.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.core.platform.BaseActivity
import com.example.myapplication.core.platform.BaseFragment

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context,MoviesActivity::class.java)
    }

    override fun fragment() = MoviesFragment()
}