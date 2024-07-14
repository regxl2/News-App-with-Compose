package com.example.news.util

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Util{
    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"

        val sources  = listOf("bbc-news", "abc-news", "al-jazeera-english")

        fun formatDate(date: String): String{
            val instant = Instant.parse(date)
            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
            return localDateTime.format(dateFormatter)
        }

        fun readingTimeEstimator(contentLength: Int): String{
            return "${(contentLength.plus(100).minus(1)).div(100)} min read"
        }

        fun encodeUrl(url: String): String{
            return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
        }

        fun decodeUrl(url: String): String{
            return URLDecoder.decode(url, StandardCharsets.UTF_8.toString())
        }
    }
}