package com.example.news.presentation.newsnavigation.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.news.data.model.Article
import com.example.news.data.repositories.newsrepository.NewsRepository
import com.example.news.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val articles = searchQuery
        .debounce(1000)
        .filter { it.isNotEmpty() || it.isNotBlank() }
        .flatMapLatest { query ->
            newsRepository.getSearchNews(searchQuery = query, sources = Util.sources)
                .map {
                    value: PagingData<Article> ->
                    value.map { article -> Util.articleToSavedArticle(article) }
                }
                .catch {
                emit(
                    PagingData.empty()
                )
            }
        }
        .cachedIn(viewModelScope)

    fun onQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }
}