package com.task.loanapplication.domain

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.task.loanapplication.data.util.RegistrationUIState
import com.task.loanapplication.data.util.UIEvent
import kotlinx.coroutines.flow.MutableStateFlow

class SignUpViewModel:ViewModel() {
    var registrationUIState= mutableStateOf(RegistrationUIState())
    var navigateToRegistration by mutableStateOf(false)
    var navigateToHomeScreen by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var signUpInProgress = mutableStateOf(false)
    var logInProgress= mutableStateOf(false)

    private val _loginSuccess =MutableStateFlow(false)
    val loginSuccess get() = _loginSuccess


    fun onEvent(event:UIEvent) {

        when (event) {

            is UIEvent.emailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is UIEvent.passwordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is UIEvent.registrationButton -> {
                signUp()
            }

            is UIEvent.loginButton -> {
                login()
            }

            is UIEvent.emailChanged -> TODO()
            UIEvent.loginButton -> TODO()
            is UIEvent.passwordChanged -> TODO()
            UIEvent.registrationButton -> TODO()
            else -> {}
        }

    }

    private fun signUp() {
        Log.d("simpleName","Inside_SignUp")
        createUserInfirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun createUserInfirebase(email:String , password:String){
        signUpInProgress.value=true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d("Firebase ", "Inside_compltelistner  ")
                Log.d("Firebase ", "is successfull =${it.isSuccessful} ")
                signUpInProgress.value=false


                if (it.isSuccessful){
                    //todo -->navigate to home screen
                    Log.d("register_success","register success ")
                    navigateToRegistration = true
                }
                else {
                    // Registration failed
                    Log.w("SignUpViewModel", "Registration failed", it.exception)
                    // For example, you can update your UI to display an error message to the user
                }
            }
            .addOnFailureListener {
                Log.d("Firebase ", "Inside_Failure ")
                Log.d("Firebase ", "Excepyion =${it.message}")
                Log.d("Firebase ", "Exception =${it.localizedMessage}")

            }

    }

    private fun login() {
        val email = registrationUIState.value.email
        val password = registrationUIState.value.password

        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Email and password cannot be empty"
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                logInProgress.value=true
                if (task.isSuccessful) {
                    Log.d("login_success","LogIn successful ")
                    _loginSuccess.value = true
                    logInProgress.value=false
                    navigateToHomeScreen= true
                } else {
                    errorMessage = "Login failed: ${task.exception?.message}"
                }
            }
    }

}