package com.example.news.presentation.newsnavigation.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.data.model.SavedArticle
import com.example.news.presentation.newsnavigation.components.ArticleCard
import com.example.news.presentation.newsnavigation.components.SearchBar
import com.example.news.presentation.newsnavigation.components.handleErrorAndLoading

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = hiltViewModel(),
    navigateToDetailScreen: (article: SavedArticle) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val query = viewModel.searchQuery.collectAsState()
    val articles = viewModel.articles.collectAsLazyPagingItems()
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(modifier = Modifier.focusRequester(focusRequester), query = query.value, onQueryChange = viewModel::onQueryChange)
        LazyColumn {
            items(articles.itemCount){ articleIndex ->
                articles[articleIndex]?.let { article -> ArticleCard(article = article, navigateToDetailScreen = navigateToDetailScreen) }
            }
            handleErrorAndLoading(articles =   articles)
        }
    }
}