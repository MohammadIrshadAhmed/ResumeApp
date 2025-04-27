package com.example.resumeandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.navigation.compose.rememberNavController
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val navController = rememberNavController()
//            MyNavHost(navController)
            val context = LocalContext.current
            val viewModel = UserViewModel(context = context)
            UserScreen(viewModel)
        }
    }
}