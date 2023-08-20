package dev.ebrahim.movies_mvvm.products.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.ebrahim.movies_mvvm.products.screens.composable.ErrorDialog
import dev.ebrahim.movies_mvvm.products.screens.composable.ListProducts
import dev.ebrahim.movies_mvvm.products.screens.composable.LoadingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListViewModel, navController: NavController) {
    val state by viewModel.state.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        ErrorDialog(
            shouldShow = state.dialogModel?.isShouldShow ?: false,
            message = state.dialogModel?.message ?: "",
            onDismiss = { viewModel.dismissDialog() },
            onConfirmClick = {
                viewModel.dismissDialog()
                viewModel.getProducts()
            })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LoadingScreen(isLoading = state.isLoading, modifier = Modifier.align(Alignment.Center))
            ListProducts(state.list,navController)
        }
    }
}
