package com.example.news.presentation.newsnavigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.news.data.model.SavedArticle

fun LazyListScope.handleErrorAndLoadingNewsScreen(articles: LazyPagingItems<SavedArticle>){
    articles.apply {
        when(loadState.refresh){
             is LoadState.NotLoading->{
                if(articles.itemCount==0){
                    item{
                        EmptyScreen(modifier = Modifier.fillParentMaxSize())
                    }
                }
            }

            is LoadState.Loading -> {
                item {
                    LoadingScreen(modifier = Modifier.fillParentMaxSize())
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorScreen(modifier = Modifier.fillParentMaxSize()){
                        retry()
                    }
                }
            }
        }
        when(loadState.append){
            is LoadState.Loading -> {
                item {
                    LoadingScreen(modifier = Modifier.fillMaxWidth())
                }
            }
            is LoadState.Error ->{
                item{
                    ErrorScreen(modifier = Modifier.fillMaxWidth()){
                        retry()
                    }
                }
            }
            else -> return
        }
    }
}

fun LazyListScope.handleErrorAndLoadingSearchScreen(articles: LazyPagingItems<SavedArticle>){
    articles.apply {
        when(loadState.refresh){
            is LoadState.NotLoading->{
                if(articles.itemCount==0){
                    item{
                        EmptyScreen(modifier = Modifier.fillParentMaxSize())
                    }
                }
            }

            is LoadState.Loading -> {
                item {
                    LoadingScreen(modifier = Modifier.fillParentMaxSize())
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorScreen(modifier = Modifier.fillParentMaxSize()){
                        retry()
                    }
                }
            }
        }
        when(loadState.append){
            is LoadState.Loading -> {
                item {
                    LoadingScreen(modifier = Modifier.fillMaxWidth())
                }
            }
            else -> return
        }
    }
}