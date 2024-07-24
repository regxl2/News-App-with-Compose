import android.net.Uri
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
import com.google.gson.Gson

@Composable
fun NewsNavigationNavGraph(paddingValues: PaddingValues, navController: NavHostController, showSnackBar: (actionPerformed: ()-> Unit)-> Unit) {
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
                val encodedArticle = Uri.encode(Gson().toJson(article))
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
                val encodedArticle = Uri.encode(Gson().toJson(article))
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
            encodedArticle?.let {
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
                .fillMaxSize(),
                showSnackBar = showSnackBar
            ){
                article ->
                val encodedArticle = Uri.encode(Gson().toJson(article))
                navController.navigate(route = "${Route.NewsNavigation.DetailScreen}/${encodedArticle}")
            }
        }
    }
}