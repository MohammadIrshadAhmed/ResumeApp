package com.example.resumeandroidapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun MyNavHost(navHostController: NavHostController) {
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
            CategoryPage(userName, onNavigate = { category ->
                navHostController.navigate("Description Page/$category")
            })
        }
        // Description Page
        composable(
            "Description Page/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "Nope"
            DescriptionPage(category)
        }
    }
}