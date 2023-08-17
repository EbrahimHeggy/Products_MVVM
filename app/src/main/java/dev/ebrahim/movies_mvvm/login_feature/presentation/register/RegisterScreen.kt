package dev.ebrahim.movies_mvvm.login_feature.presentation.register

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController, registerViewModel: RegisterViewModel) {
    val registerState by registerViewModel.signUpState.collectAsState()
    val context = LocalContext.current
    if (registerState.isUserSignSuccessfully) {
        Toast.makeText(context, stringResource(R.string.successfully_signup), Toast.LENGTH_SHORT)
            .show()
        navController.popBackStack()
        navController.navigate("login")
        registerViewModel.resetIsUserSignSuccessfullyToDefaultValue()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorDialog(
            shouldShow = registerState.dialogModel?.isShouldShow ?: false,
            message = registerState.dialogModel?.message ?: "",
            onDismiss = { registerViewModel.dismissDialog() }) {
            registerViewModel.dismissDialog()
            registerViewModel.clearTextField()
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LoadingScreen(
                isLoading = registerState.isLoading,
                modifier = Modifier.align(Alignment.Center)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.hello),
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.register),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(100.dp))

                OutlinedTextField(
                    value = registerState.email,
                    onValueChange = { registerViewModel.setEmail(it) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "email icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.email))
                    },
                    isError = Patterns.EMAIL_ADDRESS.matcher(registerState.email).matches().not()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = registerState.password,
                    onValueChange = { registerViewModel.setPassword(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(R.string.password))
                    },
                    isError = (registerState.password.length < 6),
                    leadingIcon = { Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "password icon"
                    )},
                    trailingIcon = {
                        val icon = if(registerState.isPasswordVisible){
                            Icons.Default.Visibility
                        } else{
                            Icons.Default.VisibilityOff
                        }
                        IconButton(onClick = {
                            registerViewModel.toggleShowPassword()
                        }) {
                            Icon(imageVector = icon, contentDescription = "visibility icon")

                        }
                    },
                    visualTransformation = if (registerState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        registerViewModel.register()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = (registerState.email.isNotBlank() && registerState.password.isNotBlank())
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Text(text = stringResource(R.string.already_have_account), fontSize = 20.sp)
                    Text(
                        text = stringResource(id = R.string.login),
                        fontSize = 20.sp,
                        color = Color(0xFFEF5858),
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                            navController.navigate("login")
                        }
                    )
                }

            }
        }

    }
}
