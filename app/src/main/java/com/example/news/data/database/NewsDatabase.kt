package com.example.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.data.model.Article
import com.example.news.data.model.Converters

@TypeConverters(Converters::class)
@Database(entities = [Article::class], exportSchema = false, version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}