package dev.ebrahim.movies_mvvm.login_feature.presentation.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dev.ebrahim.movies_mvvm.login_feature.data.Repository

class RegisterViewModel : ViewModel() {
    private val repository = Repository()


    private val _emailState = mutableStateOf("")
    val emailState: State<String> = _emailState
    fun setEmail(value: String) {
        _emailState.value = value
    }

    private val _passwordState = mutableStateOf("")
    val passwordState: State<String> = _passwordState
    fun setPassword(value: String) {
        _passwordState.value = value
    }


    fun addUserToFirebase() {
        if (emailState.value.isNotBlank() && passwordState.value.isNotBlank()) {
            kotlin.runCatching {
                repository.createUserWithFirebase(emailState.value, passwordState.value)

            }.getOrElse {
                Log.e("register error", it.localizedMessage.toString())
            }

        }
    }
}
