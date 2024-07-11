package com.example.news.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Util{
    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"

        fun formatDate(date: String): String{
            val instant = Instant.parse(date)
            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
            return localDateTime.format(dateFormatter)
        }

        fun readingTimeEstimator(contentLength: Int): String{
            return "${(contentLength.plus(100).minus(1)).div(100)} min read"
        }
    }
}