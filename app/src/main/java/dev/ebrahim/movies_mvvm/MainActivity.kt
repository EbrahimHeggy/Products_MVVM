package dev.ebrahim.movies_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import dev.ebrahim.movies_mvvm.details.peresntation.DetailViewModel
import dev.ebrahim.movies_mvvm.details.peresntation.DetailsScreen
import dev.ebrahim.movies_mvvm.ui.theme.Movies_MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies_MVVMTheme {
                // A surface container using the 'background' color from the theme
                val context = LocalContext.current
                val navController = rememberNavController()
                val detailsModel: DetailViewModel by viewModels()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DetailsScreen(
                        viewModel = detailsModel,
                        id = 4,
                        navController = navController,
                        context = context
                    )
                }
            }
        }
    }
}
