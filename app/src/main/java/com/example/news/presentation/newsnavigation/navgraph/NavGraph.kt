package com.example.news.presentation.newsnavigation.navgraph

import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.presentation.newsnavigation.bookmarkscreen.BookmarkScreen
import com.example.news.presentation.newsnavigation.newsscreen.NewsScreen
import com.example.news.presentation.newsnavigation.searchscreen.SearchScreen
import com.example.news.presentation.rootnavgraph.Route


@Composable
fun NewsNavigationNavGraph(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.NewsNavigation.NewsScreen.name
    ) {
        composable(route = Route.NewsNavigation.NewsScreen.name,
            enterTransition = {
                slideInHorizontally()
            }) {
            NewsScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
        }
        composable(route = Route.NewsNavigation.SearchScreen.name,
            enterTransition = {
                slideInHorizontally()
            }) {
            SearchScreen(modifier = Modifier.padding(paddingValues).fillMaxSize())
        }
        composable(route = Route.NewsNavigation.BookmarkScreen.name,
            enterTransition = {
                slideInHorizontally()
            }) {
            BookmarkScreen(modifier = Modifier.padding(paddingValues).fillMaxSize())
        }
    }
}
