package com.example.myapplication.features.movies
import com.example.myapplication.core.exception.Failure
import com.example.myapplication.core.functional.Either
import com.example.myapplication.core.functional.Either.Left
import com.example.myapplication.core.functional.Either.Right
import com.example.myapplication.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface MovieRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails()

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: Movie
    ) : MovieRepository {
        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isNetworkAvailable()){
                true -> request(
                    service.mo
                )
                false -> Left(Failure.NetworkConnection)
                }
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ) : Either<Failure, R>{
            return try{
                val response = call.execute()
                when(response.isSuccessful){
                    true-> Right(transform(response.body() ?: default))
                    false -> Left(Failure.ServerError)
                }
            }catch(exception: Throwable){
                Left(Failure.ServerError)
            }
        }
    }
}