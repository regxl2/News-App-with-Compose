package com.example.news.data.repositories.localusermanager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveOnBoardingFinish()
    fun readOnBoardingState(): Flow<Boolean>

    companion object{
        const val USER_SETTING = "userSetting"
        const val IS_ONBOARDING_FINISH = "isOnboardingFinish"
    }
}