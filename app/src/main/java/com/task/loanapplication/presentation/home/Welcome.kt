package com.task.loanapplication.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.loanapplication.R
import com.task.loanapplication.domain.SignUpViewModel

@Composable
fun HomeScreen(navController: NavController,
               signUpViewModel: SignUpViewModel
               ) {
val localContext= LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hey, Welcome Back !",
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.loanicon),
            contentDescription = "Image",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier =Modifier.height(12.dp))

        OptionButton(text = "Profile") {
            navController.navigate("home/user_profile")
        }
        OptionButton(text = "Loan Status") {
            navController.navigate("home/loan_details")
        }
        OptionButton(text = "Repayment Schedule") {
            navController.navigate("home/repayment_schedule")
        }
        OptionButton(text = "Payment History") {
            navController.navigate("details/pay_history")
        }
        OptionButton(text = "Refer Friend") {
            navController.navigate("details/rate_us")
        }

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = {
                signUpViewModel.signOut()
                navController.navigate("auth/login")
                showToast(localContext,"SignOut Successful")
               },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp)
            ,
            colors = ButtonDefaults.buttonColors(containerColor =   Color(0xFF008000))
        ) {
            Text("Logout",
                style = TextStyle(
                    fontSize = 18.sp, // Change font size
                    color = Color.White, // Change text color
                    fontWeight = FontWeight.Bold // Change font weight
                )
                )
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
@Composable
fun OptionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(1f),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
    ) {
        Text(text,
            style = TextStyle(
                fontSize = 18.sp, // Change font size
                color = Color.Black, // Change text color
                fontWeight = FontWeight.Bold // Change font weight
            )
            )
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), signUpViewModel = SignUpViewModel() )
}