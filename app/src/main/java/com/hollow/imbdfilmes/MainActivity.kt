package com.hollow.imbdfilmes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hollow.imbdfilmes.MovieViewModel
import com.hollow.imbdfilmes.ui.theme.IMBdFilmesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMBdFilmesTheme {
                val viewModel: MovieViewModel = viewModel()
	                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
	                    Box(modifier = Modifier.padding(innerPadding)) {
	                        AppNavigation(viewModel)
	                    }
	                }
            }
        }
    }
}

