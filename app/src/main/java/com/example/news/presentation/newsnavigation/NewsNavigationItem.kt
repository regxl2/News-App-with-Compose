package com.example.news.presentation.newsnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.news.presentation.rootnavgraph.Route

data class NewsNavigationItem(
    val label: String,
    val filledIcon: ImageVector,
    val outLineIcon: ImageVector,
    val route: Route
)

val newsNavigationItems = listOf(
    NewsNavigationItem(
        label = "Home",
        filledIcon = Icons.Filled.Home,
        outLineIcon = Icons.Outlined.Home,
        route = Route.NewsNavigation.NewsScreen
    ),
    NewsNavigationItem(
        label = "Search",
        filledIcon = Icons.Filled.Search,
        outLineIcon = Icons.Outlined.Search,
        route = Route.NewsNavigation.SearchScreen
    ),
    NewsNavigationItem(
        label = "Bookmark",
        filledIcon = Icons.Filled.Star,
        outLineIcon = Icons.Outlined.Star,
        route = Route.NewsNavigation.BookmarkScreen
    )
)