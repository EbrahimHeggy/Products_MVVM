package dev.ebrahim.movies_mvvm.login_feature.presentation.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import dev.ebrahim.movies_mvvm.R

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
                Text(text = stringResource(R.string.retry))
            }
        },
        title = { Text(text = stringResource(R.string.error), fontSize = 20.sp) },
        text = { Text(text = message) }
    )
}