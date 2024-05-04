package com.task.loanapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.loanapplication.data.util.RepaymentEntry

@Composable
fun RepaymentScheduleScreen(repaymentSchedule: List<RepaymentEntry>, onNextClick: () -> Unit) {
    Scaffold(
        bottomBar = {
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                // Confirm button
                Button(
                    modifier = Modifier.weight(0.4f),
                    onClick = onNextClick
                ) {
                    Text(text = "Next")
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
                )
        ) {
            Text(
                text = "Repayment Schedule",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Info bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = "Payment Date",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Payment Time",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Amount",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            // Repayment schedule list
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(repaymentSchedule) { entry ->
                    RepaymentEntryItem(entry)
                }
            }
        }
    }
}

@Composable
fun RepaymentEntryItem(entry: RepaymentEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = entry.paymentDate,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = entry.paymentTime,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Rs.${entry.amount}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RepaymentSchedulePreview() {
    RepaymentScheduleScreen(
        repaymentSchedule = getListOfSchedulePayment()
    ){}
}

fun getListOfSchedulePayment(): List<RepaymentEntry>{
    return listOf(
        RepaymentEntry("7 Dec 2023", "4.30 PM", "1000"),
        RepaymentEntry("6 Dec 2023", "4.30 PM", "1000"),
        RepaymentEntry("5 Dec 2023", "4.30 PM", "1000"),
        RepaymentEntry("4 Dec 2023", "4.30 PM", "1000"),
        RepaymentEntry("3 Dec 2023", "4.30 PM", "1000")
    )
}