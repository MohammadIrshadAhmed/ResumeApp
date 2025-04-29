package com.example.resumeandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(onNavigate: (String) -> Unit) {
    val userViewModel: UserViewModel = viewModel()
    val usersData by userViewModel.usersData.collectAsStateWithLifecycle()
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isNewUser by remember { mutableStateOf(false) }

    val isCredentialValid by remember {
        derivedStateOf {
            if (isNewUser) {
                usersData.none { it.name == userName } && password.isNotEmpty()
            } else {
                (userName == "admin" && password == "****") || usersData.any { it.name == userName && it.password == password }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (isNewUser) {
                    Text("Set your Username and Password")
                } else {
                    Text("Enter your Credentials to Login")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { newChar ->
                            val filteredChar = newChar.filter { it.isLetterOrDigit() }
                            userName = filteredChar
                        },
                        label = { Text("Username") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text("Password") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (isNewUser) {
                            userViewModel.addUser(user = User(name = userName, password = password, detail = ""))
                            isNewUser = false
                        }
                        onNavigate(userName)
                    },
                    modifier = Modifier
                        .padding(8.dp),
                    enabled = isCredentialValid,
                    colors = ButtonColors(
                        contentColor = Color.Black,
                        containerColor = Color.Yellow,
                        disabledContentColor = Color.Black,
                        disabledContainerColor = Color.Gray
                    )
                ) {
                    if (isNewUser) {
                        Text("Sign-Up")
                    } else {
                        Text("Login")
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
                if (!isNewUser) {
                    Text(
                        "Unable to login/ Forgot Password/ Sign up",
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                isNewUser = true
                            }
                        ),
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ResumeAndroidAppTheme {
        LoginPage(onNavigate = {})
    }
}