package com.task.loanapplication.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.task.loanapplication.domain.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(viewModel: MainViewModel) {
    val user by viewModel.userData.collectAsState()
    viewModel.getUserData()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {   Text(
                    text = "Register User Details",
                    textAlign = TextAlign.Center,
                    color = Color.Green,
                    fontWeight = FontWeight(500),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 18.dp)
                ) }
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
