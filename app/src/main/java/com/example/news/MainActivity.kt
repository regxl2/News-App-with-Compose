package com.example.news

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose.NewsTheme
import com.example.news.presentation.rootnavgraph.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.showSplashScreen
            }
        }
        actionBar?.hide()
        setContent {
            NewsTheme {
                NavGraph(startDestination = viewModel.startDestination)
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.isConnected.collect{
                    if(it) showConnectivityToast("You're Online")
                    else showConnectivityToast("No Internet Connection")
                }
            }
        }
    }

    private fun showConnectivityToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
