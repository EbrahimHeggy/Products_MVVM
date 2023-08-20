package dev.ebrahim.movies_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.ebrahim.movies_mvvm.login_feature.presentation.login.LoginScreen
import dev.ebrahim.movies_mvvm.login_feature.presentation.login.LoginViewModel
import dev.ebrahim.movies_mvvm.login_feature.presentation.register.RegisterScreen
import dev.ebrahim.movies_mvvm.login_feature.presentation.register.RegisterViewModel
import dev.ebrahim.movies_mvvm.login_feature.presentation.start.StartScreen
import dev.ebrahim.movies_mvvm.products.screens.home.ListScreen
import dev.ebrahim.movies_mvvm.products.screens.home.ListViewModel
import dev.ebrahim.movies_mvvm.ui.theme.Movies_MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movies_MVVMTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "start"){
                    composable("start"){
                        StartScreen(navController)
                    }
                    composable("register"){
                        val registerViewModel: RegisterViewModel by viewModels()
                        RegisterScreen(navController, registerViewModel)
                    }
                    composable("login"){
                        val loginViewModel: LoginViewModel by viewModels()
                        LoginScreen(navController, loginViewModel)
                    }
                    composable("main") {
                        val listViewModel: ListViewModel by viewModels()
                        ListScreen(viewModel = listViewModel, navController)
                    }
                }
            }
        }
    }
}