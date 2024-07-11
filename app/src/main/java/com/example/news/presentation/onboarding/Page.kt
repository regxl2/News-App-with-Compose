package com.example.news.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.news.R

data class Page(
    val title : String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to NewsPulse!",
        description = "Stay informed with the latest news from around the world. NewsConnect brings you real-time updates on current events, politics, sports, entertainment, and more.",
        image = R.drawable.news_politics
    ),
    Page(
        title = "Stay Updated, Anywhere, Anytime",
        description = "Get notifications on breaking news and trending stories. With NewsPulse, you'll never miss an important update, whether you're at home or on the go.",
        image = R.drawable.breaking_news
    ),
    Page(
        title = "Get Started with NewsConnect",
        description = "Dive into a world of news tailored just for you. Tap the button below to begin exploring!",
        image = R.drawable.sports_news
    )
)

const val TOTAL_PAGES = 3
