package dev.ebrahim.movies_mvvm.login_feature.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    Box (modifier = Modifier.fillMaxSize()){
        Text(text = "Main", fontSize = 25.sp)
    }
}