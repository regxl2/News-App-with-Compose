import android.os.Build
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.news.data.model.SavedArticle
import com.example.news.presentation.newsnavigation.bookmarkscreen.BookmarkScreen
import com.example.news.presentation.newsnavigation.newsscreen.NewsScreen
import com.example.news.presentation.newsnavigation.searchscreen.SearchScreen
import com.example.news.presentation.rootnavgraph.DetailScreenNavType
import com.example.news.presentation.rootnavgraph.Route
import com.example.news.util.Util
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
            ) { article ->
                val encodedArticle = Json.encodeToString(article.copy(url = Util.encodeUrl(article.url), urlToImage = Util.encodeUrl(article.urlToImage)))
                navController.navigate(route = "${Route.NewsNavigation.DetailScreen.name}/${encodedArticle}")
            }
        }
        composable(route = Route.NewsNavigation.SearchScreen.name,
            enterTransition = {
                slideInHorizontally()
            }) {
            SearchScreen(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) { article ->
                val encodedArticle = Json.encodeToString(article.copy(url = Util.encodeUrl(article.url), urlToImage = Util.encodeUrl(article.urlToImage)))
                navController.navigate(route = "${Route.NewsNavigation.DetailScreen.name}/${encodedArticle}")
            }
        }
        composable(route = Route.NewsNavigation.DetailScreenWithArticle.name,
            arguments = listOf(navArgument(name = "article") { type = DetailScreenNavType }),
            enterTransition = { slideInHorizontally() }
        ) { backStackEntry ->
            val encodedArticle = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            {
                backStackEntry.arguments?.getParcelable("article", SavedArticle::class.java)
            }
            else {
                @Suppress("DEPRECATION")
                backStackEntry.arguments?.getParcelable("article")
            }
            val decodedArticle = encodedArticle?.let { it.copy(url = Util.decodeUrl(it.url), urlToImage = Util.decodeUrl(it.urlToImage)) }
            decodedArticle?.let {
                DetailScreen(modifier = Modifier.fillMaxSize(), article = it) {
                    navController.navigateUp()
                }
            }
        }
        composable(route = Route.NewsNavigation.BookmarkScreen.name,
            enterTransition = {
                slideInHorizontally()
            }) {
            BookmarkScreen(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize())
        }
    }
}