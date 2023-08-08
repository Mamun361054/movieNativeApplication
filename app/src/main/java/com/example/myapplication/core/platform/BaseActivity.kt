package com.example.myapplication.core.platform

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.core.extension.inTransaction
import com.example.myapplication.databinding.ActivityLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolBarContainer.toolbar)
        addFragment(savedInstanceState)
    }


    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }
    fun toolbar() = binding.toolBarContainer.toolbar
    fun fragmentContainer() = binding.fragmentContainer

    fun progressBar() = binding.toolBarContainer.progress
    private fun addFragment(savedInstanceState: Bundle?) = savedInstanceState ?: supportFragmentManager.inTransaction {
        add(binding.fragmentContainer.id,fragment())
    }
    abstract  fun fragment() : BaseFragment
}