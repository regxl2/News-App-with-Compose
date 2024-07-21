package com.example.news.util

import com.example.news.data.model.Article
import com.example.news.data.model.SavedArticle
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

        val article = SavedArticle(
            sourceName = "NDTV News",
            title = "India vs Zimbabwe Live Score, 2nd T20I: After Abhishek Sharma's Historic Ton, India Close In On Win - NDTV Sports",
            description = "India vs Zimbabwe 2nd T20I Highlights: Abhishek Sharma's 100 off 47 helped India register a 100-run victory over Zimbabwe on Sunday.",
            url = "https://sports.ndtv.com/cricket/india-vs-zimbabwe-live-score-2nd-t20-match-7-july-ind-vs-zim-latest-scorecard-shubman-gill-sikandar-raza-6052501",
            urlToImage = "https://c.ndtvimg.com/2024-07/uu2u73kg_india-bcci-x_625x300_07_July_24.jpg?im=FaceCrop,algorithm=dnn,width=1200,height=738",
            publishedAt = "2024-07-07T14:09:34Z",
            content = "India vs Zimbabwe 2nd T20I Highlights: Abhishek Sharma starred with a historic century in India's dominating 100-run win over Zimbabwe in the second T20I in Harare on Sunday. Abhishek scored 100 off … [+991 chars]"
        )

        fun articleToSavedArticle(article: Article): SavedArticle{
            return SavedArticle(
                title = article.title,
                description = article.description,
                publishedAt = article.publishedAt,
                url = article.url,
                urlToImage = article.urlToImage,
                sourceName = article.source.name,
                content = article.content
            )
        }
    }
}