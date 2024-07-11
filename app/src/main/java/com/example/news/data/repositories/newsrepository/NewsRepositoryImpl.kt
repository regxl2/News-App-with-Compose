package com.example.news.data.repositories.newsrepository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.remote.NewsApi
import com.example.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi, private val context: Context): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = {
            NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(","), context = context)
        }
    ).flow
}