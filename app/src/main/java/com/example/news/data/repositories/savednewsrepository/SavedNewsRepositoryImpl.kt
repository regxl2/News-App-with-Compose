package com.example.news.data.repositories.savednewsrepository

import com.example.news.data.database.NewsDao
import com.example.news.data.model.SavedArticle
import kotlinx.coroutines.flow.Flow

class SavedNewsRepositoryImpl(private val newsDao: NewsDao): SavedNewsRepository {
    override suspend fun insertArticle(article: SavedArticle) {
        newsDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: SavedArticle) {
        newsDao.deleteArticle(article)
    }

    override fun getAllArticles(): Flow<List<SavedArticle>> {
        return newsDao.getAllArticles()
    }
}