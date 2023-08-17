package dev.ebrahim.movies_mvvm.login_feature.data

import dev.ebrahim.movies_mvvm.login_feature.data.firebase.Firebase

class Repository(val firebase: Firebase = Firebase()) {

    fun createUserWithFirebase(email: String, password: String) =
        firebase.createUser(email, password)

    fun loginWithFirebase(email: String, password: String) =
        firebase.login(email, password)

    fun resetPassword(email: String) = firebase.auth.sendPasswordResetEmail(email)

}