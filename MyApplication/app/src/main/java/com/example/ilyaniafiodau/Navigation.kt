package com.example.ilyaniafiodau

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/*@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "Place_list") {
        composable("Place_list") {
            PlaceListScreen(viewModel = hiltViewModel(), onPlaceClick = {
                navController.navigate("Place_Info/$it")
            })
        }
        composable("Place_Info/{PlaceId}") { backStackEntry ->
            val PlaceId = backStackEntry.arguments?.getString("PlaceId").toIntOrNull()
            if (PlaceId != null) {
                PlaceInfoScreen(PlaceId)
            } else {
                Text("Wrong ID")
            }
        }
    }
}*/

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "Place_list") {
        composable("Place_list") {
            // Passes the viewModel and navigation callback
            PlaceListScreen(viewModel = hiltViewModel(), onPlaceClick = { placeId ->
                // Navigates to the "Place_Info/{PlaceId}" route when a place is clicked
                navController.navigate("Place_Info/$placeId")
            })
        }
        composable("Place_Info/{PlaceId}") { backStackEntry ->
            // Get the placeId from arguments
            val placeId = backStackEntry.arguments?.getString("PlaceId")?.toIntOrNull()
            if (placeId != null) {
                // Passes placeId to the screen
                PlaceInfoScreen(placeId)
            } else {
                // If the placeId is invalid or null
                Text("Wrong ID")
            }
        }
    }
}
