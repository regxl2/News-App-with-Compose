package com.example.news.data.repositories.newsrepository

import androidx.paging.PagingData
import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun getSearchNews(sources: List<String>, searchQuery: String): Flow<PagingData<Article>>
}