package com.example.news.di

import android.content.Context
import com.example.news.data.remote.NewsApi
import com.example.news.data.repositories.localusermanager.LocalUserManager
import com.example.news.data.repositories.localusermanager.LocalUserManagerImpl
import com.example.news.data.repositories.newsrepository.NewsRepository
import com.example.news.data.repositories.newsrepository.NewsRepositoryImpl
import com.example.news.domain.usecases.getnewsusecase.GetNewsUseCase
import com.example.news.domain.usecases.onboardingusecase.OnBoardingUseCase
import com.example.news.domain.usecases.onboardingusecase.ReadOnBoardingState
import com.example.news.domain.usecases.onboardingusecase.SaveOnBoardingState
import com.example.news.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideLocalUserManager(@ApplicationContext context: Context): LocalUserManager {
        return LocalUserManagerImpl(context);
    }

    @Singleton
    @Provides
    fun provideOnBoardingUseCase(
        saveOnBoardingState: SaveOnBoardingState,
        readOnBoardingState: ReadOnBoardingState
    ): OnBoardingUseCase {
        return OnBoardingUseCase(saveOnBoardingState, readOnBoardingState)
    }

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun getNewsRepository(newsApi: NewsApi, @ApplicationContext context: Context): NewsRepository {
        return NewsRepositoryImpl(newsApi, context)
    }

    @Singleton
    @Provides
    fun getNews(newsRepository: NewsRepository): GetNewsUseCase{
        return GetNewsUseCase(newsRepository)
    }

}