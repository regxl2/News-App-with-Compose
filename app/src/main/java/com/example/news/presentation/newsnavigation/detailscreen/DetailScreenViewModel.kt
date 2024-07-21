package com.example.news.presentation.newsnavigation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.SavedArticle
import com.example.news.data.repositories.savednewsrepository.SavedNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel@Inject constructor(private val savedNewsRepository: SavedNewsRepository): ViewModel() {
    fun saveArticle(article: SavedArticle){
        viewModelScope.launch {
            savedNewsRepository.insertArticle(article)
        }
    }
}