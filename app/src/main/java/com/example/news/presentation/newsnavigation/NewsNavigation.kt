package com.example.news.presentation.newsnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.newsnavigation.navgraph.NewsNavigationNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigation(modifier: Modifier = Modifier) {
    val newsNavigationController = rememberNavController()
    val navBackStackEntry by newsNavigationController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "NewsPulse",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            })
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxWidth()) {
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
    ) { paddingValues ->
        NewsNavigationNavGraph(
            paddingValues = paddingValues,
            navController = newsNavigationController
        )
    }
}

@Preview
@Composable
private fun PreviewNewsNavigation() {
    NewsNavigation()
}