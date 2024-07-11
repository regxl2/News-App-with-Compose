package com.example.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.usecases.onboardingusecase.OnBoardingUseCase
import com.example.news.presentation.rootnavgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase): ViewModel() {
    var showSplashScreen by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.OnBoardingScreen.name)
        private set
    init {
        onBoardingUseCase.readOnBoardingState().onEach { isOnBoardingDone->
            startDestination = if(isOnBoardingDone) Route.NewsNavigation.name
            else Route.OnBoardingScreen.name
            delay(300)
            showSplashScreen = false
        }.launchIn(viewModelScope)
    }
}