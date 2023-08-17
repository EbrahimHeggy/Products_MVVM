package dev.ebrahim.movies_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.ebrahim.movies_mvvm.screens.details.DetailsScreen
import dev.ebrahim.movies_mvvm.screens.home.ListScreen
import dev.ebrahim.movies_mvvm.screens.home.ListViewModel
import dev.ebrahim.movies_mvvm.ui.theme.Movies_MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies_MVVMTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "home") {
                        composable("Home") {
                            val listViewModel: ListViewModel by viewModels()
                            ListScreen(viewModel = listViewModel, navController)
                        }
                        composable("Details") {

                            DetailsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

