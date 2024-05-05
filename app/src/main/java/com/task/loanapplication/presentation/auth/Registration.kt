package com.task.loanapplication.presentation.auth

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.loanapplication.data.util.User
import com.task.loanapplication.domain.MainViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun RegistrationScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val localContext= LocalContext.current
    var userName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var panNumber by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }
    var electricBillNo by remember { mutableStateOf("") }
    var bankingStatementAct by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var accountIFSC by remember { mutableStateOf("") }
    var housingReciptNo by remember { mutableStateOf("") }
    var selectedPhotoUri by remember { mutableStateOf<Uri?>(null) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.size(150.dp)) {
            // Photo Selector
            Image(
                imageVector = Icons.Filled.Person,
                contentDescription = "Select Photo",
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.BottomStart)
                    .background(Color.LightGray.copy(0.5f), shape = RoundedCornerShape(12.dp))
                    .clickable {
                        // Open photo selector here
                    }
            )
            Icon(modifier = Modifier.align(Alignment.TopEnd),imageVector = Icons.Filled.Edit, contentDescription = "Upload Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Username Field
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("UserName") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Username Field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Address Field
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PAN Number Field
        OutlinedTextField(
            value = panNumber,
            onValueChange = { panNumber = it },
            label = { Text("PAN Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mobile Number Field
        OutlinedTextField(
            value = mobileNo,
            onValueChange = { mobileNo = it },
            label = { Text("Aadhar Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Electric Bill Number Field
        OutlinedTextField(
            value = electricBillNo,
            onValueChange = { electricBillNo = it },
            label = { Text("Electric Bill Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Banking Statement Account Field
        OutlinedTextField(
            value = bankingStatementAct,
            onValueChange = { bankingStatementAct = it },
            label = { Text("Bank Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Banking Statement Account Field
        OutlinedTextField(
            value =accountNumber ,
            onValueChange = { accountNumber= it },
            label = { Text("Account Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Banking Statement IFSC Code
        OutlinedTextField(
            value = accountIFSC,
            onValueChange = { accountIFSC = it },
            label = { Text("IFSC Code") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val onRent by viewModel.isLivingOnRent.collectAsState()

        // Housing Receipt Number Field
        OutlinedTextField(
            value = housingReciptNo,
            onValueChange = { housingReciptNo = it },
            label = { Text("House Tax Receipt Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = onRent,
                onCheckedChange = { viewModel.updateOnRent(it) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Do you live on Rent",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Register Button
        Button(
            onClick = {
                if (userName.isNotBlank() &&
                    phoneNumber.isNotBlank() &&
                    address.isNotBlank() &&
                    panNumber.isNotBlank() &&
                    mobileNo.isNotBlank() &&
                    electricBillNo.isNotBlank() &&
                    bankingStatementAct.isNotBlank() &&
                    accountNumber.isNotBlank() &&
                    accountIFSC.isNotBlank() &&
                    housingReciptNo.isNotBlank())
                {
                    val user= User(
                        userName = userName,
                        phoneNo = phoneNumber,
                        address = address,
                        panNumber = panNumber,
                        aadharNumber = mobileNo,
                        electricBillNo = electricBillNo,
                        bankName = bankingStatementAct,
                        accountNumber = bankingStatementAct, // Assuming this is correct, please correct if needed
                        accountIFSC = bankingStatementAct, // Assuming this is correct, please correct if needed
                        taxReceiptNumber = housingReciptNo
                    );
                    viewModel.registerUserInfo(user)
                    navController.navigate("auth/upload_docs")
                    viewModel.updateUserData(user)
                }else{
                    showToast(localContext,"Please fill all the fields")
                }
            },
            modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor =Color(0xFF008000))
        ) {
            Text(text = "Submit",
                style = TextStyle(
                    fontSize = 18.sp, // Change font size
                    color = Color.Black, // Change text color
                    fontWeight = FontWeight.Bold))
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun PreviewRegistrationScreen() {
    RegistrationScreen(navController = rememberNavController(), viewModel = MainViewModel())
    
}