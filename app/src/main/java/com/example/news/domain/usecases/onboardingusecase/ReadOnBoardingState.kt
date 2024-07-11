package com.example.news.domain.usecases.onboardingusecase

import com.example.news.data.repositories.localusermanager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingState @Inject constructor(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readOnBoardingState()
    }
}