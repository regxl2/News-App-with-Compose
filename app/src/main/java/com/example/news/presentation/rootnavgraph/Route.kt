package com.example.news.presentation.rootnavgraph

sealed class Route(val name: String){
    data object OnBoardingScreen: Route(name = "onBoardingScreen")
    data object NewsNavigation: Route(name = "newsNavigation"){
        data object NewsScreen: Route(name = "newsScreen")
        data object SearchScreen: Route(name = "searchScreen")
        data object DetailScreen: Route(name = "detailScreen")
        data object DetailScreenWithUrl: Route(name = "detailScreen/{url}")
        data object BookmarkScreen: Route(name = "bookmarkScreen")
    }
}