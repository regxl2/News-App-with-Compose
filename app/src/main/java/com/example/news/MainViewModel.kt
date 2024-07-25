package com.example.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repositories.connectivityManager.ConnectivityRepository
import com.example.news.data.repositories.localusermanager.LocalUserManager
import com.example.news.presentation.rootnavgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val localUserManager: LocalUserManager, connectivityRepository: ConnectivityRepository): ViewModel() {
    var showSplashScreen by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.OnBoardingScreen.name)
        private set

    val isConnected = connectivityRepository
        .getNetworkStatus()
        .distinctUntilChanged()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5_000))

    init {
        localUserManager.readOnBoardingState().onEach { isOnBoardingDone->
            startDestination = if(isOnBoardingDone) Route.NewsNavigation.name
            else Route.OnBoardingScreen.name
            delay(300)
            showSplashScreen = false
        }.launchIn(viewModelScope)
    }
}