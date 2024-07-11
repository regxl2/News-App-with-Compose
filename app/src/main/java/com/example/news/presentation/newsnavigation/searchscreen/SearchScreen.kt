package com.example.news.presentation.newsnavigation.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.presentation.newsnavigation.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val focusRequester = remember { FocusRequester() }
    val query = viewModel.query.collectAsState()
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(modifier = Modifier.focusRequester(focusRequester), query = query.value, onQueryChange = viewModel::onQueryChange)
    }
}