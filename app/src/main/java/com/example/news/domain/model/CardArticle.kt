package com.example.news.domain.model

data class CardArticle(
    val title: String,
    val description: String,
    val source: Source,
    val urlToImage: String,
    val publishedAt: String
)