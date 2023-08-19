package dev.ebrahim.movies_mvvm.login_feature.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.ebrahim.movies_mvvm.R
import dev.ebrahim.movies_mvvm.login_feature.presentation.composable.ErrorDialog
import dev.ebrahim.movies_mvvm.login_feature.presentation.composable.LoadingScreen
import dev.ebrahim.movies_mvvm.login_feature.presentation.composable.ResetPasswordDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel) {
    var resetDialogShow by remember {
        mutableStateOf(false)
    }
    val loginState by loginViewModel.loginState.collectAsState()
    val context = LocalContext.current
    if (loginState.isUserLoginSuccessfully) {
        Toast.makeText(context, stringResource(R.string.welcome_to_you), Toast.LENGTH_SHORT).show()
        navController.popBackStack("start", true)
        navController.navigate("main")
        loginViewModel.resetIsUserLoginSuccessfullyToDefaultValue()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorDialog(
            shouldShow = loginState.dialogModel?.isShouldShow ?: false,
            message = loginState.dialogModel?.message ?: "",
            onDismiss = { loginViewModel.dismissDialog() },
            onConfirm = {
                loginViewModel.dismissDialog()
                loginViewModel.clearTextField()
            }
        )
        ResetPasswordDialog(
            shouldShow = resetDialogShow,
            emailValue = loginState.emailInDialog,
            onEmailValueChange = { loginViewModel.setEmailInDialog(it) },
            resetPassword = { loginViewModel.resetPassword() },
            clearDialogTextField = { loginViewModel.clearDialogTextField() },
            onDismiss = { resetDialogShow = false }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LoadingScreen(isLoading = loginState.isLoading)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.welcome_back),
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(100.dp))

                OutlinedTextField(
                    value = loginState.email,
                    onValueChange = { loginViewModel.setEmail(it) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "email icon"
                        )
                    },
                    label = {
                        Text(text = stringResource(id = R.string.email))
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = loginState.password,
                    onValueChange = { loginViewModel.setPassword(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "password icon"
                        )
                    },
                    trailingIcon = {
                        val icon = if (loginState.isPasswordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        }
                        IconButton(onClick = {
                            loginViewModel.toggleShowPassword()
                        }) {
                            Icon(imageVector = icon, contentDescription = "visibility icon")
                        }
                    },
                    visualTransformation = if (loginState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(R.string.forgot_password),
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            resetDialogShow = true
                        }
                        .align(Alignment.End))
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        loginViewModel.login()
                    },
                    colors = ButtonDefaults.buttonColors(),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = (loginState.email.isNotBlank() && loginState.password.isNotBlank())
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Text(text = stringResource(R.string.don_t_have_an_account), fontSize = 20.sp)
                    Text(
                        text = stringResource(id = R.string.register),
                        fontSize = 20.sp,
                        color = Color(0xFFEF5858),
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                            navController.navigate("register")
                        }
                    )
                }
            }
        }
    }
}