package com.example.news.data.repositories.newsrepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.remote.NewsApi
import com.example.news.di.Apikey
import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi, @Apikey val apikey: String) :
    NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = {
            NewsPagingSource(
                newsApi = newsApi,
                sources = sources.joinToString(","),
                apiKey = apikey
            )
        }
    ).flow

    override fun getSearchNews(sources: List<String>, searchQuery: String): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(","),
                    apikey = apikey
                )
            }
        ).flow
}