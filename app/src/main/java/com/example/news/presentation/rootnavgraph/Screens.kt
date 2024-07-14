package com.example.news.presentation.rootnavgraph

import com.example.news.data.model.Article
import kotlinx.serialization.Serializable


@Serializable
object OnBoardingScreen

@Serializable
object NewsNavigationScreen

@Serializable
object NewsScreen

@Serializable
object SearchScreen

@Serializable
object BookmarkScreen

@Serializable
data class DetailScreen(val article: Article)