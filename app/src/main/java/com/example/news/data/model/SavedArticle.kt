package com.example.news.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity
data class SavedArticle(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable