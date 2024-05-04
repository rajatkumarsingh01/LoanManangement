package com.task.loanapplication.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoanDetailsScreen(
    loanSanctioned: String,
    loanDistributedDate: String,
    loanEndDate: String,
    onDownloadLoanAgreementClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Loan Details", style = TextStyle(fontSize = 24.sp))
        Text(text = "Loan Sanctioned: $loanSanctioned")
        Text(text = "Loan Distributed Date: $loanDistributedDate")
        Text(text = "Loan End Date: $loanEndDate")
        Button(
            onClick = { onDownloadLoanAgreementClick() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Download Loan Agreement")
        }
    }
}
