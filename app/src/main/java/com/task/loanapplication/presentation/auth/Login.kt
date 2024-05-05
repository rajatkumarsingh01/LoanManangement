package com.task.loanapplication.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.task.loanapplication.R
import com.task.loanapplication.data.util.UIEvent
import com.task.loanapplication.domain.MainViewModel
import com.task.loanapplication.domain.SignUpViewModel

@Composable
fun LoginScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
     viewModel: MainViewModel,
    onRegisterClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color.White)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Saudagar Trading & Company",
            textAlign = TextAlign.Center,
            color = Color.Green,
            fontWeight = FontWeight(700),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Loan Management",
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            fontWeight = FontWeight(400),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Username Field
        MyTextField(
            labelValue = stringResource(id = R.string.email),
            imageVector = Icons.Default.Email,
            onTextSelected = {
                signUpViewModel.onEvent(UIEvent.emailChanged(it))
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        MyPasswordField(
            labelValue = stringResource(id = R.string.password),
            imageVector = Icons.Default.Lock,
            onTextSelected = {
                signUpViewModel.onEvent(UIEvent.passwordChanged(it))
            },
        )

        Spacer(modifier = Modifier.height(78.dp))

        // Login Button
        Button(
            onClick = {
                signUpViewModel.onEvent(UIEvent.loginButton)
            },
            modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(text = "Login",
                style = TextStyle(
                    fontSize = 18.sp, // Change font size
                    color = Color.Black, // Change text color
                    fontWeight = FontWeight.Bold // C
                )
            )


        }

        if (signUpViewModel.logInProgress.value){
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        OutlinedButton(
            onClick = { onRegisterClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register",
                style = TextStyle(
                    fontSize = 18.sp, // Change font size
                    color = Color.Black, // Change text color
                    fontWeight = FontWeight.Bold // C
                ))
        }
    }


}

@Preview
@Composable
private fun  PreviewLoginScreen() {
    LoginScreen(signUpViewModel = SignUpViewModel(), viewModel = MainViewModel(), onRegisterClick = {})
}