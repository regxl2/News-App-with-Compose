package com.example.news.presentation.rootnavgraph

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.news.data.model.SavedArticle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class Route(val name: String){
    data object OnBoardingScreen: Route(name = "onBoardingScreen")
    data object NewsNavigation: Route(name = "newsNavigation"){
        data object NewsScreen: Route(name = "newsScreen")
        data object SearchScreen: Route(name = "searchScreen")
        data object DetailScreen: Route(name = "detailScreen")
        data object DetailScreenWithArticle: Route(name = "detailScreen/{article}")
        data object BookmarkScreen: Route(name = "bookmarkScreen")
    }
}

val DetailScreenNavType = object: NavType<SavedArticle>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): SavedArticle? {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, SavedArticle::class.java)
        }
        else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun put(bundle: Bundle, key: String, value: SavedArticle) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): SavedArticle {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: SavedArticle): String {
        return Json.encodeToString(value = value)
    }

}