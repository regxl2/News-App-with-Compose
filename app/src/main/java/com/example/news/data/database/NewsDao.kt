package com.example.news.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.model.SavedArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: SavedArticle)

    @Delete
    suspend fun deleteArticle(article: SavedArticle)

    @Query("Select * from SavedArticle")
    fun getAllArticles(): Flow<List<SavedArticle>>
}