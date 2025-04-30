package com.example.resumeandroidapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun MyNavHost(navHostController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()
    NavHost(navController = navHostController, startDestination = "Login Page") {
        // Login Page
        composable("Login Page") {
            LoginPage(onNavigate = { userName ->
                navHostController.navigate("Category Page/$userName")
            })
        }
        // Category Page
        composable(
            "Category Page/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("username") ?: ""
            CategoryPage(userName,
                userViewModel = userViewModel, // No need to pass it in route it will be directly recognized
                onNavigate = { category ->
                navHostController.navigate("Description Page/$category")
            })
        }
        // Description Page
        composable(
            "Description Page/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "Nope"
            DescriptionPage(category, userViewModel)
        }
    }
}