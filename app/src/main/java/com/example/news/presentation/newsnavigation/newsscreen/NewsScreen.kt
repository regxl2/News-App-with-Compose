package com.example.news.presentation.newsnavigation.newsscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.data.model.SavedArticle
import com.example.news.presentation.newsnavigation.components.ArticleCard
import com.example.news.presentation.newsnavigation.components.handleErrorAndLoadingNewsScreen


@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsScreenViewModel = hiltViewModel(),
    navigateToDetailScreen: (article: SavedArticle) -> Unit
) {
    val articles = viewModel.articles.collectAsLazyPagingItems()
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(articles.itemCount) { articleIndex ->
            articles[articleIndex]?.let { article ->
                ArticleCard(
                    article = article,
                    navigateToDetailScreen = navigateToDetailScreen
                )
            }
        }
        handleErrorAndLoadingNewsScreen(articles = articles)
    }
}



