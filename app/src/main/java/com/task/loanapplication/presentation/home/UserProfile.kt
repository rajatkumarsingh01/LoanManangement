package com.task.loanapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.loanapplication.data.util.RepaymentEntry
import com.task.loanapplication.data.util.User
import com.task.loanapplication.domain.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(viewModel: MainViewModel) {
    val user by viewModel.userData.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User Profile") }
            )
        },
        content = {it
            user?.let { userProfile ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "User Profile",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    ProfileDetail(label = "User Name", value = userProfile.userName)
                    ProfileDetail(label = "Phone No", value = userProfile.phoneNo)
                    ProfileDetail(label = "Address", value = userProfile.address)
                    ProfileDetail(label = "PAN Number", value = userProfile.panNumber)
                    ProfileDetail(label = "Aadhar Number", value = userProfile.aadharNumber)
                    ProfileDetail(label = "Electric Bill No", value = userProfile.electricBillNo)
                    ProfileDetail(label = "Bank Name", value = userProfile.bankName)
                    ProfileDetail(label = "Account Number", value = userProfile.accountNumber)
                    ProfileDetail(label = "IFSC", value = userProfile.accountIFSC)
                    ProfileDetail(label = "Tax Receipt", value = userProfile.taxReceiptNumber)
                }
            }
        }
    )
}



@Composable
fun ProfileDetail(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
