package com.example.news.presentation.newsnavigation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.news.data.model.SavedArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissArticle(
    modifier: Modifier = Modifier,
    article: SavedArticle,
    swipeToRemoveArticle: (article: SavedArticle) -> Unit,
    navigateToDetailScreen: (article: SavedArticle) -> Unit
) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(confirmValueChange = {
        when (it) {
            SwipeToDismissBoxValue.StartToEnd -> {
                swipeToRemoveArticle(article)
            }
            else -> return@rememberSwipeToDismissBoxState false
        }
        true
    }, positionalThreshold = { it * 0.25f })
    SwipeToDismissBox(modifier = modifier, state = swipeToDismissBoxState, backgroundContent = {},
        enableDismissFromEndToStart = false) {
        ArticleCard(article = article, navigateToDetailScreen = navigateToDetailScreen)
    }
}
