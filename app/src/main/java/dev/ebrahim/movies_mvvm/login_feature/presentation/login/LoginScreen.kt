package dev.ebrahim.movies_mvvm.login_feature.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Welcome Back",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = viewModel.emailState.value,
            onValueChange = { viewModel.setEmail(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "email")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = viewModel.passwordState.value,
            onValueChange = { viewModel.setPassword(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "password")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Forgot Password?", color = Color.Blue, modifier = Modifier
            .clickable {
            }
            .align(Alignment.End))
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                      viewModel.loginUsingFirebase()
            },
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login", fontSize = 20.sp, modifier = Modifier.padding(4.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Text(text = "Don't have an account? ", fontSize = 20.sp)
            Text(
                text = "Register",
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
