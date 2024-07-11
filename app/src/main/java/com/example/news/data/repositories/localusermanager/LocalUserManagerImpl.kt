package com.example.news.data.repositories.localusermanager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(private val context: Context) : LocalUserManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LocalUserManager.USER_SETTING)

    private object PreferencesKeys {
        val IS_ONBOARDING_FINISH = booleanPreferencesKey(LocalUserManager.IS_ONBOARDING_FINISH)
    }

    override suspend fun saveOnBoardingFinish() {
        context.dataStore.edit { preference ->
            preference[PreferencesKeys.IS_ONBOARDING_FINISH] = true
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preference ->
                preference[PreferencesKeys.IS_ONBOARDING_FINISH] ?: false
            }
    }
}