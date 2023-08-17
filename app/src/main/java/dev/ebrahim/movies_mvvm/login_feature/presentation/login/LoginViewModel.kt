package dev.ebrahim.movies_mvvm.login_feature.presentation.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.ebrahim.movies_mvvm.login_feature.data.Repository

class LoginViewModel : ViewModel() {
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


    fun loginUsingFirebase() {
        val result = repository.loginWithFirebase(emailState.value, passwordState.value)
    }

    fun resetPassword(context: Context, email: String) {
        repository.firebase.auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "send email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show();
            }
        }
    }
}







