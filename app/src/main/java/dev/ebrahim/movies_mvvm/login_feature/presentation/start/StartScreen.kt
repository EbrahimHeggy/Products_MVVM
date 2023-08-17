package dev.ebrahim.movies_mvvm.login_feature.presentation.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.ebrahim.movies_mvvm.R

@Composable
fun StartScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.commerce_start_icon),
            contentDescription = "start screen Icon",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Welcome",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFEF5858)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Browse all of our products and get what you want",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(onClick = {
            navController.navigate("register")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create Account", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = {
                                 navController.navigate("login")
                                 }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login", fontSize = 20.sp)
        }

    }


}