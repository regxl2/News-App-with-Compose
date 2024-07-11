package com.example.news.presentation.newsnavigation.searchscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchScreenViewModel @Inject constructor(): ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun onQueryChange(newQuery: String){
        _query.value = newQuery
    }
}