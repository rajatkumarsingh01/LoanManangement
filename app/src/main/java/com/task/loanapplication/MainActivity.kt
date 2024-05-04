package com.task.loanapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.task.loanapplication.domain.MainViewModel
import com.task.loanapplication.domain.SignUpViewModel
import com.task.loanapplication.navigation.NavigationForApp
import com.task.loanapplication.ui.theme.LoanApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val signUpViewModel by lazy { ViewModelProvider(this)[SignUpViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            LoanApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationForApp(navHostController = navController, viewModel,signUpViewModel)


                }
            }
        }
    }
}
