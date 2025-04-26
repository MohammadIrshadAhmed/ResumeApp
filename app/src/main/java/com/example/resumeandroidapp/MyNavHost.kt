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
            LoginPage(onNavigate = {
                navHostController.navigate("Category Page")
            })
        }
        // Category Page
        composable("Category Page") {
            CategoryPage(onNavigate =  { category ->
                navHostController.navigate("Description Page/$category")
            })
        }
        // Description Page
        composable("Description Page/{category}",
            arguments = listOf(navArgument("category"){ type = NavType.StringType})) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "Nope"
            DescriptionPage(category)
        }
    }
}