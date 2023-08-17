package dev.ebrahim.movies_mvvm.details.peresntation

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import dev.ebrahim.movies_mvvm.details.peresntation.composable.DetailsScreenComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailViewModel,
    id: Int,
    navController: NavController,
    context: Context
) {
    val state by viewModel.status.collectAsState()
    viewModel.getProductWithId(id)

    DetailsScreenComponent(product = state.product, onAddToCartClicked = {
        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
    }, onBackClicked = {
        navController.navigate("home")
    })




}





