package dev.ebrahim.movies_mvvm.login_feature.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.ebrahim.movies_mvvm.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordDialog(
    shouldShow: Boolean,
    emailValue: String,
    onEmailValueChange: (String) -> Unit,
    resetPassword: () -> Unit,
    clearDialogTextField: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!shouldShow) return
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.reset_your_password),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = emailValue,
                    onValueChange = onEmailValueChange,
                    label = { Text(text = stringResource(id = R.string.email)) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    resetPassword()
                    clearDialogTextField()
                    onDismiss()
                }) {
                    Text(text = stringResource(R.string.reset))
                }
            }
        }
    }
}
