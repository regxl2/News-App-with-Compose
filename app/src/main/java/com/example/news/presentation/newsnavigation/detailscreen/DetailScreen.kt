import android.content.Context
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.data.model.SavedArticle
import com.example.news.presentation.newsnavigation.detailscreen.DetailScreenViewModel
import com.example.news.util.Util
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(modifier: Modifier = Modifier, article: SavedArticle , viewModel: DetailScreenViewModel = hiltViewModel(),  navigateUp: ()-> Unit) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember{ SnackbarHostState() }
    val context = LocalContext.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(modifier = Modifier.fillMaxWidth(), url = article.url, title = article.title, context = context, navigateUp = navigateUp){
                viewModel.saveArticle(article)
                scope.launch {
                    snackBarHostState.showSnackbar("Article saved successfully")
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        WebViewCompose(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), url =  article.url)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(modifier: Modifier = Modifier, title: String,  url: String, context: Context,  navigateUp: () -> Unit, onClickSaveArticle: ()-> Unit) {
    TopAppBar(
        modifier = modifier,
        title = {  },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back button"
                )
            }
        },
        actions = {
            IconButton(onClick = onClickSaveArticle) {
                Icon(imageVector = Icons.Outlined.Star, contentDescription = "save button")
            }
            IconButton(onClick = {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    putExtra(Intent.EXTRA_TITLE, title)
                    type = "text/plain"
                }
                context.startActivity(sendIntent)
            }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "share button")
            }
        }
    )
}

@Composable
fun WebViewCompose(modifier: Modifier = Modifier, url: String) {
    AndroidView(
        modifier = modifier,
        factory ={ context->
            WebView(context).apply {
                webViewClient = WebViewClient()
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}

@Preview
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(modifier = Modifier.fillMaxSize(), article = Util.article, navigateUp = {})
}