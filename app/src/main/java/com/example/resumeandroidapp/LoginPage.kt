package com.example.resumeandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(onNavigate: () -> Unit) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Enter your Credentials to Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)) // change background color to know it's significance
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
                        onValueChange = { password = it },
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
                    onClick = { onNavigate() },
                    modifier = Modifier
                        .padding(8.dp),
                    colors = ButtonColors(contentColor = Color.Black, containerColor = Color.Yellow, disabledContentColor = Color.Black, disabledContainerColor = Color.Gray)
                ) {
                    Text("Login")
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