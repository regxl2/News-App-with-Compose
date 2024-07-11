package com.example.news.data.remote

import com.example.news.data.database.News
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): News

    @GET("everything")
    suspend fun searchNews(
        @Query("searchQuery") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): News
}