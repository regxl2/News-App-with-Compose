package com.example.news.presentation.newsnavigation.bookmarkscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.news.data.model.SavedArticle
import com.example.news.presentation.newsnavigation.components.SwipeToDismissArticle


@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkScreenViewModel = hiltViewModel(),
    showSnackBar: (actionPerformed: () -> Unit) -> Unit,
    navigateToDetailScreen: (article: SavedArticle) -> Unit
) {
    val articles by viewModel.savedArticle.collectAsStateWithLifecycle()
    LazyColumn(modifier = modifier) {
        items(count = articles.size, key = { index ->
            articles[index].id
        }) { index ->
            SwipeToDismissArticle(
                article = articles[index],
                navigateToDetailScreen = navigateToDetailScreen,
                swipeToRemoveArticle = { article ->
                    viewModel.deleteArticle(article)
                    showSnackBar {
                        viewModel.insertArticle(article = article)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBookmarkScreen() {
    BookmarkScreen(showSnackBar = {actionPerformed ->  }, navigateToDetailScreen = {})
}