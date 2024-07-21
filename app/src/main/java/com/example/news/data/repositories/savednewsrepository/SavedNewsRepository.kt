package com.example.news.data.repositories.savednewsrepository

import com.example.news.data.model.SavedArticle
import kotlinx.coroutines.flow.Flow

interface SavedNewsRepository {
    suspend fun insertArticle(article: SavedArticle)

    suspend fun deleteArticle(article: SavedArticle)

    fun getAllArticles(): Flow<List<SavedArticle>>
}