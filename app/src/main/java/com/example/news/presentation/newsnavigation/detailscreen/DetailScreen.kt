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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.presentation.newsnavigation.detailscreen.DetailScreenViewModel

@Composable
fun DetailScreen(modifier: Modifier = Modifier, url: String , navigateUp: ()-> Unit, viewModel: DetailScreenViewModel = hiltViewModel()) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            DetailTopBar(modifier = Modifier.fillMaxWidth(), navigateUp = navigateUp){

            }
        }) { paddingValues ->
        WebViewCompose(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), url = url)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(modifier: Modifier = Modifier, navigateUp: () -> Unit, onClickSaveArticle: ()-> Unit) {
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
            IconButton(onClick = { /*TODO*/ }) {
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
    DetailScreen(modifier = Modifier.fillMaxSize(), url = " ", navigateUp = {})
}