package com.example.news.presentation.newsnavigation.newsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.domain.model.Article
import com.example.news.domain.usecases.getnewsusecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(getNews: GetNewsUseCase) : ViewModel() {
    var articles: MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty())
        private set

    init {
        viewModelScope.launch {
            getNews(listOf("bbc-news", "abc-news", "al-jazeera-english")).cachedIn(viewModelScope).collect {
                articles.value = it
            }
        }
    }
}