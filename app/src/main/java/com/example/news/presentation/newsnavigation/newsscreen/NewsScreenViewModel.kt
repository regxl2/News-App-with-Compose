package com.example.news.presentation.newsnavigation.newsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.news.data.model.SavedArticle
import com.example.news.data.repositories.newsrepository.NewsRepository
import com.example.news.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {
    var articles: MutableStateFlow<PagingData<SavedArticle>> = MutableStateFlow(PagingData.empty())
        private set

    init {
        viewModelScope.launch {
            newsRepository.getNews(sources = Util.sources).cachedIn(viewModelScope).collect {
                articles.value = it.map { article ->
                    Util.articleToSavedArticle(article)
                }
            }
        }
    }
}