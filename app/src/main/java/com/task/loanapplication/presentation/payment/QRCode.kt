package com.task.loanapplication.presentation.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.loanapplication.R

@Composable
fun QRCodeScreen(
    onDoneClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Spacer(modifier = Modifier.weight(0.6f))
                // Cancel button
                OutlinedButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = onCancelClick
                ) {
                    Text(text = "Cancel",
                        style = TextStyle(
                            fontSize = 18.sp, // Change font size
                            color = Color.Black, // Change text color
                            fontWeight = FontWeight.Bold // C
                        ))
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Done button
                Button(
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    onClick = onDoneClick
                ) {
                    Text(text = "Done",
                        style = TextStyle(
                            fontSize = 18.sp, // Change font size
                            color = Color.Black, // Change text color
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

            Text(
                text = "Complete Payment",
                textAlign = TextAlign.Center,
                color = Color.Green,
                fontWeight = FontWeight(500),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            // Info text
            Text(
                text = "Download to scan this QR for daily payment",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // QR code
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(id = R.drawable.dummy_qr),
                contentDescription = "QR Code"
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Thank You! Your Registration is Successfully Completed. Please Proceed with the Login Process.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )


        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun QRCodePreview() {
    QRCodeScreen(onCancelClick = {}, onDoneClick = {})
}