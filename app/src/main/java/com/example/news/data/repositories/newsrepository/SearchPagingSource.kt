package com.example.news.data.repositories.newsrepository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.news.data.remote.NewsApi
import com.example.news.di.Apikey
import com.example.news.data.model.Article

class SearchPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String,
    @Apikey private val apikey: String,
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            val page = params.key?:1
            val response = newsApi.searchNews(searchQuery = searchQuery, page = page, sources = sources, apiKey = apikey)
            val articles = response.articles.distinctBy { it.title }
            return LoadResult.Page(
                data = articles,
                prevKey = if(page==1) null else page.minus(1),
                nextKey = if(page==articles.size) null else page.plus(1)
            )
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}