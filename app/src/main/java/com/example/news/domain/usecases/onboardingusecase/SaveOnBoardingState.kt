package com.example.news.domain.usecases.onboardingusecase

import com.example.news.data.repositories.localusermanager.LocalUserManager
import javax.inject.Inject

class SaveOnBoardingState @Inject constructor(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        return localUserManager.saveOnBoardingFinish()
    }
}
