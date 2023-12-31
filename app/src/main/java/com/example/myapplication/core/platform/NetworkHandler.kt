package com.example.myapplication.core.platform

import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import com.example.myapplication.core.extension.connectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler
@Inject constructor(@ApplicationContext private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val connectionActivityManager = context.connectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectionActivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectionActivityManager.getNetworkCapabilities((network)) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo = connectionActivityManager.activeNetworkInfo ?: return false;
            @Suppress("DEPRECATION") return networkInfo.isConnected
        }
    }
}