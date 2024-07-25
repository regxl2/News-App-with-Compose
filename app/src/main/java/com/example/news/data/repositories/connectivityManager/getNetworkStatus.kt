package com.example.news.data.repositories.connectivityManager

import android.net.Network
import android.net.NetworkCapabilities
import android.net.ConnectivityManager
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun ConnectivityManager.getNetworkStatus() = callbackFlow<Boolean> {
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            trySend(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            trySend(false)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val internetCapable =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            val wifiTransport = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            val cellularTransport = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            trySend(internetCapable && ( wifiTransport or cellularTransport ))
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            trySend(false)
        }
    }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    registerNetworkCallback(networkRequest, networkCallback)

    awaitClose {
        unregisterNetworkCallback(networkCallback)
    }
}