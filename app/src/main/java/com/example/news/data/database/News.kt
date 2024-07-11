package com.example.news.data.database

import com.example.news.domain.model.Article

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)