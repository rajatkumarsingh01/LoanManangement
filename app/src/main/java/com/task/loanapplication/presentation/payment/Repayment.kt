package com.task.loanapplication.presentation.payment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.loanapplication.R
import com.task.loanapplication.domain.MainViewModel

@Composable
fun RepaymentOptionScreen(
    viewModel: MainViewModel,
    navController: NavController

) {
    val userData by viewModel.userData.collectAsState()
    val phoneNumber=userData?.phoneNo?: ""
    val localContext= LocalContext.current
    val loanAmount by viewModel.selectedLoan.collectAsState()
    val dailyPayment = when(loanAmount){
        "3 Lakhs" -> "1000"
        "4 Lakhs" -> "1500"
        "5 Lakhs" -> "1750"
        "8 Lakhs" -> "2000"
        else -> " "
    }

    // SMS checkbox
    var isChecked by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                // Confirm button
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008000)),
                    onClick = {
                        if (isChecked){
                            sendNotification(localContext)
                            navController.navigate("payment/qr_code")

                         showToast(localContext,"SMS sent !")
                        }
                    }
                ) {
                    Text(text = "Confirm",
                        style = TextStyle(
                            fontSize = 18.sp, // Change font size
                            color = Color.White, // Change text color
                            fontWeight = FontWeight.Bold // C
                        )
                        )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.loanicon),
                contentDescription = "Image",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Info message
            Text(
                text = "Reschedule payment & timing",
                textAlign = TextAlign.Center,
                color = Color(0xFF008000),
                fontWeight = FontWeight(400),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            // Duration input field
            Text(
                text = "Loan Amount: $loanAmount",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Daily payment input field
            Text(
                text = "Daily Payment: $dailyPayment",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Timing (display only)
            Text(
                text = "Timing: 8:00 PM",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    modifier = Modifier.padding(end = 8.dp)

                )
                Text(
                    text = "I hereby agree to receive SMS from the application",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

private const val CHANNEL_ID = "com.example.notification.channel"
// Notification ID
private const val NOTIFICATION_ID = 123 // You can use any unique integer value
// Function to send notification
fun sendNotification(context: Context) {
    // Create NotificationManager instance
    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    // Create notification channel (required for Android Oreo and above)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Notification Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }
    // Build the notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("Fincorp Loan Solutions")
        .setContentText("Your loan repayment has been confirmed.")
        .setSmallIcon(R.drawable.loanicon)
    // You can add more configurations like actions, intent, etc.
    // Show the notification
    notificationManager.notify(NOTIFICATION_ID, builder.build())
}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
private fun PreviewRepaymentOptionScreen() {
    RepaymentOptionScreen(viewModel = MainViewModel(), rememberNavController())
}

