package dev.ebrahim.movies_mvvm.login_feature.presentation.login

import androidx.lifecycle.ViewModel
import dev.ebrahim.movies_mvvm.login_feature.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val repository = Repository()
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun setEmail(value: String) {
        _loginState.update {
            it.copy(email = value)
        }
    }

    fun setEmailInDialog(value: String) {
        _loginState.update {
            it.copy(emailInDialog = value)
        }
    }

    fun setPassword(value: String) {
        _loginState.update {
            it.copy(password = value)
        }
    }


    fun login() {
        _loginState.update { it.copy(isLoading = true) }
        val currentUser = repository.firebase.auth.currentUser
        repository.loginWithFirebase(loginState.value.email, loginState.value.password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful && currentUser!!.isEmailVerified) {
                    // user in database and verified
                    _loginState.update {
                        it.copy(isLoading = false, isUserLoginSuccessfully = true)
                    }
                    return@addOnCompleteListener
                }
                _loginState.update {
                    it.copy(
                        isLoading = false,
                        isUserLoginSuccessfully = false,
                        dialogModel = DialogModel(
                            isShouldShow = true,
                            message = authResult.exception.toString()
                        )
                    )
                }
            }
    }

    fun resetPassword() {
        _loginState.update { it.copy(isLoading = true) }
        repository.resetPassword(loginState.value.emailInDialog).addOnCompleteListener {
            if (it.isSuccessful) {
                _loginState.update { loginState1 ->
                    loginState1.copy(isLoading = false)
                }
                return@addOnCompleteListener
            }
            _loginState.update { loginState ->
                loginState.copy(
                    isLoading = false,
                    dialogModel = DialogModel(
                        isShouldShow = true,
                        message = it.exception.toString()
                    )
                )
            }
        }
    }

    fun dismissDialog() {
        _loginState.update {
            it.copy(dialogModel = null)
        }
    }

    fun clearTextField() {
        _loginState.update {
            it.copy(email = "", password = "")
        }
    }

    fun clearDialogTextField() {
        _loginState.update {
            it.copy(emailInDialog = "")
        }
    }
    fun resetIsUserLoginSuccessfullyToDefaultValue(){
        _loginState.update {
            it.copy(isUserLoginSuccessfully = false)
        }
    }

    fun toggleShowPassword(){
        _loginState.update {
            it.copy(isPasswordVisible = it.isPasswordVisible.not())
        }
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val isUserLoginSuccessfully: Boolean = false,
    val email: String = "",
    val emailInDialog: String = "",
    val password: String = "",
    val dialogModel: DialogModel? = null,
    val isPasswordVisible: Boolean = false
)

data class DialogModel(
    val isShouldShow: Boolean = false,
    val message: String
)







