package com.example.news.presentation.newsnavigation

import NewsNavigationNavGraph
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.rootnavgraph.Route
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigation(modifier: Modifier = Modifier) {
    val newsNavigationController = rememberNavController()
    val navBackStackEntry by newsNavigationController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackBarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            if (isNotDetailScreen(currentDestination)) {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "NewsPulse",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                })
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {
            if(isNotDetailScreen(currentDestination)){
                NewsBottomBar(
                    currentDestination = currentDestination,
                    newsNavigationController = newsNavigationController
                )
            }
        }
    ) { paddingValues ->
        NewsNavigationNavGraph(
            paddingValues = paddingValues,
            navController = newsNavigationController
        ){ actionPerformed->
            scope.launch {
                val result = snackBarHostState.showSnackbar(
                    message = "Article deleted successfully",
                    actionLabel = "UNDO",
                    duration = SnackbarDuration.Long
                )
                when(result){
                    SnackbarResult.ActionPerformed -> actionPerformed()
                    else -> return@launch
                }
            }
        }
    }
}

@Composable
fun NewsBottomBar(modifier: Modifier = Modifier, currentDestination: NavDestination?, newsNavigationController: NavController) {
    BottomAppBar(modifier = modifier.fillMaxWidth()) {
        newsNavigationItems.forEach { item ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == item.route.name } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    newsNavigationController.navigate(item.route.name) {
                        popUpTo(newsNavigationController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.filledIcon else item.outLineIcon,
                        contentDescription = item.label
                    )
                })
        }
    }
}


fun isNotDetailScreen (currentDestination: NavDestination?):Boolean{
    return currentDestination?.hierarchy?.any { it.route == Route.NewsNavigation.DetailScreenWithArticle.name} == false
}
