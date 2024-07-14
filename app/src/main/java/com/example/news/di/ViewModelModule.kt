package com.example.news.di

import com.example.news.data.database.NewsDao
import com.example.news.data.remote.NewsApi
import com.example.news.data.repositories.newsrepository.NewsRepository
import com.example.news.data.repositories.newsrepository.NewsRepositoryImpl
import com.example.news.data.repositories.savednewsrepository.SavedNewsRepository
import com.example.news.data.repositories.savednewsrepository.SavedNewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @ViewModelScoped
    @Provides
    fun getNewsRepository(newsApi: NewsApi, @Apikey apikey: String): NewsRepository {
        return NewsRepositoryImpl(newsApi, apikey)
    }

    @ViewModelScoped
    @Provides
    fun getSavedNewsRepository(newsDao: NewsDao): SavedNewsRepository{
        return SavedNewsRepositoryImpl(newsDao)
    }
}