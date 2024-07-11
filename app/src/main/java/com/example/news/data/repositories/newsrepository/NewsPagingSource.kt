package com.example.news.data.repositories.newsrepository

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.news.data.remote.NewsApi
import com.example.news.domain.model.Article

class NewsPagingSource(private val newsApi: NewsApi, private val sources: String, private val context: Context):
    PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try{
            val page = params.key?:1
            val apiKey = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA).metaData.getString("API_KEY", "")
            val response = newsApi.getNews(page,sources, apiKey)
            val articles = response.articles.distinctBy {
                it.title
            }
            LoadResult.Page(
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