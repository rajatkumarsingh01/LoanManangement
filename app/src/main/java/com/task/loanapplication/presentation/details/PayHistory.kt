package com.task.loanapplication.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.loanapplication.data.util.Payment

@Composable
fun PaymentHistoryScreen(
    paymentList: List<Payment>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Info text
        Text(
            text = "Payment History",
            style = MaterialTheme.typography.headlineSmall
        )

        // Payment history list
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(paymentList) { payment ->
                PaymentItem(payment = payment)
                Divider()
            }
        }

        // Rate app button
    }
}

@Composable
fun PaymentItem(payment: Payment) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        // Loan amount
        Text(
            text = "Loan Amount: ${payment.loanAmount}",
            style = MaterialTheme.typography.bodyLarge
        )

        // Instalment
        Text(
            text = "Instalment: ${payment.instalment}",
            style = MaterialTheme.typography.bodyLarge
        )

        // Date and time
        Text(
            text = "Date and Time: ${payment.dateTime}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PaymentHistoryPreview() {
    PaymentHistoryScreen(paymentList = getSamplePaymentHistory())
}

fun getSamplePaymentHistory(): List<Payment> {
    val paymentList = mutableListOf<Payment>()

    // Add sample payments to the list
    paymentList.add(Payment("3 Lakh", "10000", "12 Dec 2023, 1:00 PM"))
    paymentList.add(Payment("4 Lakh", "12000", "12 Dec 2023, 1:00 PM"))
    paymentList.add(Payment("5 Lakh", "15000", "12 Dec 2023, 1:00 PM"))
    paymentList.add(Payment("8 Lakh", "20000", "12 Dec 2023, 1:00 PM"))

    return paymentList
}