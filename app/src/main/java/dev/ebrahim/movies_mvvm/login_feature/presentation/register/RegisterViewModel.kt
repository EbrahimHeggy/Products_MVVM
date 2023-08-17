package dev.ebrahim.movies_mvvm.login_feature.presentation.register

import androidx.lifecycle.ViewModel
import dev.ebrahim.movies_mvvm.login_feature.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val repository = Repository()


    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()


    //    private val _emailState = mutableStateOf("")
//    val emailState: State<String> = _emailState
    fun setEmail(value: String) {
//        _emailState.value = value
        _signUpState.update {
            it.copy(email = value)
        }
    }

    //    private val _passwordState = mutableStateOf("")
//    val passwordState: State<String> = _passwordState
    fun setPassword(value: String) {
//        _passwordState.value = value

        _signUpState.update {
            it.copy(password = value)
        }
    }


    fun register() {
        _signUpState.update { it.copy(isLoading = true) }
        repository.createUserWithFirebase(signUpState.value.email, signUpState.value.password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    verifyEmail()
                    _signUpState.update { state ->
                        state.copy(isLoading = false, isUserSignSuccessfully = true)
                    }
                    return@addOnCompleteListener
                }
                _signUpState.update { state ->
                    state.copy(
                        isLoading = false,
                        isUserSignSuccessfully = false,
                        errorFlag = authResult.exception.toString(),
                        dialogModel = DialogModel(
                            isShouldShow = true,
                            message = authResult.exception.toString()
                        )
                    )
                }
            }
    }

    private fun verifyEmail() {
        val currentUser = repository.firebase.auth.currentUser
        currentUser?.sendEmailVerification()
    }

    fun dismissDialog() {
        _signUpState.update {
            it.copy(dialogModel = null)
        }
    }

    fun clearTextField(){
        _signUpState.update {
            it.copy(email = "", password = "")
        }
    }

    fun resetIsUserSignSuccessfullyToDefaultValue(){
        _signUpState.update {
            it.copy(isUserSignSuccessfully = false)
        }
    }

    fun toggleShowPassword(){
        _signUpState.update {
            it.copy(isPasswordVisible = it.isPasswordVisible.not())
        }
    }
}

data class SignUpState(
    val isLoading: Boolean = false,
    val isUserSignSuccessfully: Boolean = false,
    val errorFlag: String = "",
    val email: String = "",
    val password: String = "",
    val dialogModel: DialogModel? = null,
    val isPasswordVisible: Boolean = false
)

data class DialogModel(
    val isShouldShow: Boolean = false,
    val message: String
)
