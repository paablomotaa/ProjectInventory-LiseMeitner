package com.example.inventory.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.features.inventorycreation.ui.creation.InventoryCreationViewModel
import app.features.inventorylist.ui.InventoryListViewModel
import com.example.inventory.navigation.InventoryGraph
import com.example.inventory.navigation.InventoryGraph.inventoryGraph

@Composable
fun HomeScreen(navController: NavHostController,inventoryListViewModel: InventoryListViewModel,inventoryCreationViewModel: InventoryCreationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = InventoryGraph.ROUTE) {
        inventoryGraph(
            navController,
            inventoryListViewModel,
            inventoryCreationViewModel
        )
    }
}