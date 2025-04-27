package com.example.resumeandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()
    val isUserDataLoading by viewModel.isUserDataLoading.collectAsState()
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White), Arrangement.SpaceEvenly) {
        Spacer(modifier = Modifier.fillMaxWidth().height(48.dp))
        if (isUserDataLoading) {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            if (users.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("No users found")
                }
            } else {
                users.forEach { user: User ->
                    Text(text = "User name = ${user.name} , User id = ${user.id}")
                }
            }
            Button(onClick = { viewModel.addUser(User(name = "new user"))}) {
                Text(text = "Add User")
            }
            Button(onClick = { viewModel.deleteAllUsers()}) {
                Text(text = "Clear All data")
            }
        }
    }
}

@Preview
@Composable
fun UserScreenPreview() {
    ResumeAndroidAppTheme {
        UserScreen(viewModel = UserViewModel(LocalContext.current))
    }
}