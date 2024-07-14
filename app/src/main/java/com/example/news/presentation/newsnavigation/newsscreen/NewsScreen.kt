package com.example.news.presentation.newsnavigation.newsscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.data.model.Article
import com.example.news.presentation.newsnavigation.components.ArticleCard
import com.example.news.presentation.newsnavigation.components.ErrorScreen
import com.example.news.presentation.newsnavigation.components.LoadingScreen
import com.example.news.presentation.newsnavigation.components.handleErrorAndLoading


@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsScreenViewModel = hiltViewModel(),
    navigateToDetailScreen: (url: String) -> Unit
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
        handleErrorAndLoading(articles = articles)
    }
}



