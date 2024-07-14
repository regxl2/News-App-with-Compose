package com.example.news.data.repositories.savednewsrepository

import com.example.news.data.database.NewsDao
import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

class SavedNewsRepositoryImpl(private val newsDao: NewsDao): SavedNewsRepository {
    override suspend fun insertArticle(article: Article) {
        newsDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
    }
}