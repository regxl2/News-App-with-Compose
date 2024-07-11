package com.example.news.presentation.rootnavgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.newsnavigation.NewsNavigation
import com.example.news.presentation.onboarding.OnBoardingScreen

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Route.OnBoardingScreen.name){
            OnBoardingScreen(modifier = Modifier.fillMaxSize())
        }
        composable(route = Route.NewsNavigation.name){
            NewsNavigation(modifier = Modifier.fillMaxSize())
        }
    }
}