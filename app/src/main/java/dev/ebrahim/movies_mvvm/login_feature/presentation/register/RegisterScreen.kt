package dev.ebrahim.movies_mvvm.login_feature.presentation.register

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
fun RegisterScreen(navController: NavHostController, registerViewModel: RegisterViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello", modifier = Modifier.align(Alignment.Start), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Register",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = registerViewModel.emailState.value,
            onValueChange = { registerViewModel.setEmail(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "email")
            },
//            isError = Patterns.EMAIL_ADDRESS.matcher(email).matches().not()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = registerViewModel.passwordState.value,
            onValueChange = { registerViewModel.setPassword(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "password")
            },
//            isError = (password.length < 8).not()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                registerViewModel.addUserToFirebase()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register", fontSize = 20.sp, modifier = Modifier.padding(4.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Text(text = "Already have account? ", fontSize = 20.sp)
            Text(
                text = "login",
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