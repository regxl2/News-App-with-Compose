package com.example.news.domain.usecases.getnewsusecase

import androidx.paging.PagingData
import com.example.news.data.repositories.newsrepository.NewsRepository
import com.example.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(private val newsRepository: NewsRepository){
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources)
    }
}