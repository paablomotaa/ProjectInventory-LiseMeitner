package com.example.inventory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.features.inventorycreation.ui.creation.InventoryCreationViewModel
import app.features.inventorycreation.ui.creation.inventoryCreationScreen
import app.features.inventorydetail.ui.InventoryDetails
import app.features.inventorydetail.ui.InventoryDetailsViewModel
import app.features.inventorylist.ui.InventoryListScreen
import app.features.inventorylist.ui.InventoryListViewModel

object InventoryGraph{
    const val ROUTE = "InventoryGraph"

    fun inventoryCreate() = "$ROUTE/inventorycreation"
    fun inventoryList() = "$ROUTE/inventorylist"
    fun inventoryDetails() = "$ROUTE/inventorydetails/{inventoryId}"
    fun inventoryDetails(code:Int) = "$ROUTE/inventorydetails/$code"
    fun NavGraphBuilder.inventoryGraph(
        navController: NavController,
        inventorylistviewmodel: InventoryListViewModel,
        inventorycreateviewmodel: InventoryCreationViewModel,
        inventoryDetailsViewModel: InventoryDetailsViewModel,
        onOpenDrawer: () -> Unit
    ){
        navigation(startDestination = inventoryList(), route = ROUTE){
            inventoryList(navController, inventorylistviewmodel,onOpenDrawer)
            inventoryCreation(navController, inventorycreateviewmodel)
            inventoryDetails(navController,onOpenDrawer,inventoryDetailsViewModel)
        }
    }
    private fun NavGraphBuilder.inventoryCreation(navController: NavController,inventorycreateviewmodel: InventoryCreationViewModel){
        composable(route = inventoryCreate()){
            inventoryCreationScreen(
                goBack = {navController.popBackStack()},
                viewmodel = inventorycreateviewmodel,
            )
        }
    }
    private fun NavGraphBuilder.inventoryList(
        navController: NavController,
        inventorylistviewmodel: InventoryListViewModel,
        onOpenDrawer: () -> Unit
    ){
        composable(route = inventoryList()){
            InventoryListScreen(
                goAdd = {navController.navigate(inventoryCreate())},
                onOpenDrawer = onOpenDrawer,
                viewModel = inventorylistviewmodel,
                goDetails = {inventario -> navController.navigate(inventoryDetails(inventario.id))}
            )
        }
    }
    private fun NavGraphBuilder.inventoryDetails(
        navController: NavController,
        onOpenDrawer: () -> Unit,
        inventoryDetailsViewModel: InventoryDetailsViewModel
    ){
        composable(
            route = "$ROUTE/inventorydetails/{inventoryId}",
            arguments = listOf(navArgument("inventoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val inventoryId = backStackEntry.arguments?.getInt("inventoryId")

            if (inventoryId != null) {
                InventoryDetails(
                    onBack = { navController.popBackStack() },
                    inventoryId = inventoryId,
                    viewModel = inventoryDetailsViewModel,
                )
            }
        }
    }
}