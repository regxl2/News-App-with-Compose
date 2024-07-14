package com.example.news.data.repositories.savednewsrepository

import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

interface SavedNewsRepository {
    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getAllArticles(): Flow<List<Article>>
}