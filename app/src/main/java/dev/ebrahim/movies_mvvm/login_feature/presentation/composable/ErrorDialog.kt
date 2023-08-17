package dev.ebrahim.movies_mvvm.login_feature.presentation.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(
    shouldShow: Boolean = false,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!shouldShow) return
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text(text = "Retry")
            }
        },
        title = { Text(text = "Error", fontSize = 20.sp) },
        text = { Text(text = message) }
    )
}