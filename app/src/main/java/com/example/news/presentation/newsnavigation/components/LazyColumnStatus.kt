package com.example.news.presentation.newsnavigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.news.data.model.Article

fun LazyListScope.handleErrorAndLoading(articles: LazyPagingItems<Article>){
    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item {
                    LoadingScreen(modifier = Modifier.fillParentMaxSize())
                }
            }

            loadState.refresh is LoadState.Error -> {
                item {
                    ErrorScreen(modifier = Modifier.fillParentMaxSize()){
                        retry()
                    }
                }
            }

            loadState.append is LoadState.Loading -> {
                item {
                    LoadingScreen(modifier = Modifier.fillMaxWidth())
                }
            }

            loadState.append is LoadState.Error -> {
                item {
                    ErrorScreen(modifier = Modifier.fillMaxWidth()) {
                        retry()
                    }
                }
            }
        }
    }
}