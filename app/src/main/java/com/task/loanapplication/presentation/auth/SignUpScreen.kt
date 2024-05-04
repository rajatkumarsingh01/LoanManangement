package com.task.loanapplication.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.task.loanapplication.R
import com.task.loanapplication.data.util.UIEvent
import com.task.loanapplication.domain.SignUpViewModel


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Please signUp ",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            textAlign = TextAlign.Center
            )

        Spacer(modifier = Modifier.height(16.dp))
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

        // Register Button
        OutlinedButton(
            onClick = {
                signUpViewModel.onEvent(UIEvent.registrationButton)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "SignUp")
        }

        if (signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue:String, imageVector: ImageVector,
                onTextSelected: (String) -> Unit,
){

    val textValue= remember {
        mutableStateOf("") }


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        label = {Text (text = labelValue)},
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true ,
        maxLines = 1,
        value = textValue.value,
        onValueChange ={ newValue->
            textValue.value=newValue
            onTextSelected(newValue)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icon"
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPasswordField(
    labelValue:String, imageVector: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus:Boolean=false
){
    val localFocusManager= LocalFocusManager.current

    val password= remember {
        mutableStateOf("") }

    val passwordVisible= remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        label = {Text (text = labelValue)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange ={ newValue->
            password.value=newValue
            onTextSelected(newValue)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icon"
            )
        },
        trailingIcon = {
            val iconImage=if ( passwordVisible.value) Icons.Filled.Check else Icons.Filled.Clear
            var description=if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value}) {
                Icon(imageVector=iconImage, contentDescription =description )
            }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation() ,

    )
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
  SignUpScreen(onRegisterClick = {})
}