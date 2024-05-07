package com.task.loanapplication.presentation.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.task.loanapplication.domain.SignUpViewModel

@Composable
fun LoginScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    onRegisterClick: () -> Unit,
) {
    val localContext= LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp)

        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.loanicon),
                contentDescription = "App Logo",
                modifier = Modifier.size(50.dp) // Adjust size as needed
            )
            Column {
                Text(
                    text = "Fincorp Loan Solutions",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF008000),
                    fontWeight = FontWeight(300),
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 10.dp, start = 6.dp)
                )
                Text(
                    text = "Helping the Needy...",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF008000),
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 10.dp, start = 6.dp)
                )
            }
        }
              Spacer(modifier = Modifier.height(20.dp))

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
                    colors = ButtonDefaults.buttonColors(containerColor =   Color(0xFF008000)),
            border = BorderStroke(2.dp, color = Color.Gray),
        ) {
            Text(text = "Login",
                style = TextStyle(
                    fontSize = 18.sp, // Change font size
                    color = Color.White, // Change text color
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
    LoginScreen(signUpViewModel = SignUpViewModel(), onRegisterClick = {})
}