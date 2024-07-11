package com.example.news.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.presentation.onboarding.components.OnBoardingPage
import com.example.news.presentation.onboarding.components.PageIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, viewModel: OnBoardingViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState {
        TOTAL_PAGES
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        HorizontalPager(modifier = Modifier.weight(0.9f), state = pagerState) {
            OnBoardingPage(data = pages[it])
        }
        if(pagerState.currentPage == TOTAL_PAGES-1){
            Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { viewModel.setOnBoardingFinish() }) {
                Text(text = "Get Started")
            }
        }
        PageIndicator(modifier = Modifier.weight(0.1f), index = pagerState.currentPage)
    }
}

@Preview
@Composable
private fun PreviewOnBoardingScreen() {
    OnBoardingScreen()
}