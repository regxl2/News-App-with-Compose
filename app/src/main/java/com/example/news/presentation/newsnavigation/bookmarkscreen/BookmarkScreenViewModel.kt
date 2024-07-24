package com.example.news.presentation.newsnavigation.bookmarkscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.SavedArticle
import com.example.news.data.repositories.savednewsrepository.SavedNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkScreenViewModel@Inject constructor(private val savedNewsRepository: SavedNewsRepository): ViewModel() {
    private val _savedArticles = MutableStateFlow<List<SavedArticle>>(listOf())
    val savedArticle = _savedArticles.asStateFlow()

    init {
        loadAllArticles()
    }

    private fun loadAllArticles(){
        viewModelScope.launch {
            savedNewsRepository
                .getAllArticles()
                .collect{
                        articles -> _savedArticles.value = articles
                }
        }
    }

    fun insertArticle(article: SavedArticle){
        viewModelScope.launch {
            savedNewsRepository.insertArticle(article)
        }
    }

    fun deleteArticle(article: SavedArticle){
        viewModelScope.launch {
            savedNewsRepository.deleteArticle(article)
        }
    }

}