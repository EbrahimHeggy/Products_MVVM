package dev.ebrahim.movies_mvvm.login_feature.data

import android.util.Log
import android.widget.Toast
import dev.ebrahim.movies_mvvm.login_feature.data.firebase.Firebase

class Repository(val firebase: Firebase = Firebase()) {


    fun createUserWithFirebase(email: String, password: String) {

        firebase.createUser(email, password).addOnCompleteListener {
            try {
                if(it.isSuccessful){
                    verifyEmail()
                } else{
                    Log.e("verify Error", it.exception?.localizedMessage.toString())
                    throw it.exception!!
                }
            } catch (e : Exception){
                Log.e("repo", e.localizedMessage.toString())
                throw e
            }


        }

    }




    fun loginWithFirebase(email: String, password: String): Boolean {
        var result = false
        firebase.login(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                // user in database
                val currentUser = firebase.auth.currentUser
                if (currentUser!!.isEmailVerified) {
                    result = true

                } else {
                    Log.e("Login Error", it.exception?.localizedMessage.toString())
                    result = false
                }

            } else {
                Log.e("Login Error", it.exception?.localizedMessage.toString())
                result = false
            }
        }
        return result
    }


    private fun verifyEmail(){
        val currentUser = firebase.auth.currentUser
        currentUser?.sendEmailVerification()
    }

}