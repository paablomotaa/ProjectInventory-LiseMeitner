package com.example.inventory.navigation

import androidx.hilt.navigation.compose.hiltViewModel
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
import app.features.inventorycreation.ui.edition.InventoryEditionViewModel
import app.features.inventorycreation.ui.edition.inventoryEditionScreen
import app.features.inventorydetail.ui.productinventory.InventoryProductsList
import app.features.inventorydetail.ui.productinventory.InventoryProductsViewModel

object InventoryGraph {
    const val ROUTE = "InventoryGraph"

    fun inventoryCreate() = "$ROUTE/inventorycreation"
    fun inventoryList() = "$ROUTE/inventorylist"
    fun inventoryDetails() = "$ROUTE/inventorydetails/{inventoryId}"
    fun inventoryEdit() = "$ROUTE/inventoryedit/{inventoryId}"
    fun inventoryProductsList() = "$ROUTE/inventoryproductslist/{inventoryId}"

    fun NavGraphBuilder.inventoryGraph(
        navController: NavController,
        onOpenDrawer: () -> Unit
    ) {
        navigation(startDestination = inventoryList(), route = ROUTE) {
            inventoryList(navController, onOpenDrawer)
            inventoryCreation(navController)
            inventoryDetails(navController, onOpenDrawer)
            inventoryEdit(navController)
            inventoryProductsList(navController)
        }
    }

    private fun NavGraphBuilder.inventoryCreation(navController: NavController) {
        composable(route = inventoryCreate()) {
            val inventoryCreateViewModel = hiltViewModel<InventoryCreationViewModel>()
            inventoryCreationScreen(
                goBack = { navController.popBackStack() },
                viewmodel = inventoryCreateViewModel
            )
        }
    }

    private fun NavGraphBuilder.inventoryList(
        navController: NavController,
        onOpenDrawer: () -> Unit
    ) {
        composable(route = inventoryList()) {
            val inventoryListViewModel = hiltViewModel<InventoryListViewModel>()
            InventoryListScreen(
                goAdd = { navController.navigate(inventoryCreate()) },
                onOpenDrawer = onOpenDrawer,
                viewModel = inventoryListViewModel,
                goDetails = { inventario -> navController.navigate("InventoryGraph/inventorydetails/${inventario.id}") }
            )
        }
    }

    private fun NavGraphBuilder.inventoryDetails(
        navController: NavController,
        onOpenDrawer: () -> Unit
    ) {
        composable(
            route = inventoryDetails(),
            arguments = listOf(navArgument("inventoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val inventoryId = backStackEntry.arguments?.getInt("inventoryId")
            val inventoryDetailsViewModel = hiltViewModel<InventoryDetailsViewModel>()

            if (inventoryId != null) {
                InventoryDetails(
                    onBack = { navController.popBackStack() },
                    inventoryId = inventoryId,
                    viewModel = inventoryDetailsViewModel,
                    goEdit = { navController.navigate("InventoryGraph/inventoryedit/$inventoryId") },
                    goToAdd = {navController.navigate("InventoryGraph/inventoryproductslist/$inventoryId")}
                )
            }
        }
    }

    private fun NavGraphBuilder.inventoryEdit(navController: NavController) {
        composable(
            route = inventoryEdit(),
            arguments = listOf(navArgument("inventoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val inventoryId = backStackEntry.arguments?.getInt("inventoryId")
            val inventoryEditionViewModel = hiltViewModel<InventoryEditionViewModel>()

            if (inventoryId != null) {
                inventoryEditionViewModel.getInventory(inventoryId)

                inventoryEditionScreen(
                    goBack = { navController.popBackStack() },
                    onAccept = { navController.navigate(inventoryList()) },
                    viewModel = inventoryEditionViewModel,
                    navController = navController
                )
            }
        }
    }
    private fun NavGraphBuilder.inventoryProductsList(navController: NavController){

        composable(
            route = inventoryProductsList(),
            arguments = listOf(navArgument("inventoryId") {type = NavType.IntType})
        ){ backStackEntry ->
            val inventoryId = backStackEntry.arguments?.getInt("inventoryId")
            val inventoryProductsListViewModel = hiltViewModel<InventoryProductsViewModel>()
            if(inventoryId != null){
                InventoryProductsList(
                    onBack = {navController.popBackStack()},
                    viewModel = inventoryProductsListViewModel,
                    inventoryId = inventoryId
                )
            }
        }
    }
}