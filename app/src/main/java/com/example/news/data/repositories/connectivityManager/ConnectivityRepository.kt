package com.example.news.data.repositories.connectivityManager

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityRepository(context: Context) {
    private val connectivityManager = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    fun getNetworkStatus() = connectivityManager.getNetworkStatus()
}