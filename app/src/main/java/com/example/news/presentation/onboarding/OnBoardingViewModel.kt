package com.example.news.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.usecases.onboardingusecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase):ViewModel(){
    fun setOnBoardingFinish(){
        viewModelScope.launch {
            onBoardingUseCase.saveOnBoardingState()
        }
    }
}