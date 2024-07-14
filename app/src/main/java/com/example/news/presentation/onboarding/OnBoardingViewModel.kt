package com.example.news.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repositories.localusermanager.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val localUserManager: LocalUserManager):ViewModel(){
    fun setOnBoardingFinish(){
        viewModelScope.launch {
            localUserManager.saveOnBoardingFinish()
        }
    }
}