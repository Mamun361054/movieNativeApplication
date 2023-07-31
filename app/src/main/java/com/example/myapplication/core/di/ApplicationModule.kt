package com.example.myapplication.core.di

import com.example.myapplication.features.movies.MovieRepository
import dagger.Module
import  dagger.Provides
import  dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import  retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
class ApplicationModule {

@Provides
@Singleton
fun provideRetrofit() : Retrofit{
    return Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

    private fun createClient() : OkHttpClient{
      val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        return  okHttpClientBuilder.build()
    };

    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: MovieRepository.Network) : MovieRepository = dataSource
}