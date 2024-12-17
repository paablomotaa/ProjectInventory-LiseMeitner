package com.example.inventory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.inventorycreation.ui.creation.InventoryCreationViewModel
import app.features.inventorycreation.ui.creation.inventoryCreationScreen
import app.features.inventorylist.ui.InventoryListScreen
import app.features.inventorylist.ui.InventoryListViewModel

object InventoryGraph{
    const val ROUTE = "InventoryGraph"

    fun inventoryCreate() = "$ROUTE/inventorycreation"
    fun inventoryList() = "$ROUTE/inventorylist"
    fun inventoryEdit() = "$ROUTE/inventoryedit"

    fun NavGraphBuilder.inventoryGraph(
        navController: NavController,
        inventorylistviewmodel: InventoryListViewModel,
        inventorycreateviewmodel: InventoryCreationViewModel,
        onOpenDrawer: () -> Unit
    ){
        navigation(startDestination = InventoryGraph.inventoryList(), route = InventoryGraph.ROUTE){
            inventoryList(navController, inventorylistviewmodel,onOpenDrawer)
            inventoryCreation(navController, inventorycreateviewmodel)
        }
    }
    private fun NavGraphBuilder.inventoryCreation(navController: NavController,inventorycreateviewmodel: InventoryCreationViewModel){
        composable(route = InventoryGraph.inventoryCreate()){
            inventoryCreationScreen(
                goBack = {navController.popBackStack()},
                viewmodel = inventorycreateviewmodel,
                navController = navController
            )
        }
    }
    private fun NavGraphBuilder.inventoryList(
        navController: NavController,
        inventorylistviewmodel: InventoryListViewModel,
        onOpenDrawer: () -> Unit
    ){
        composable(route = InventoryGraph.inventoryList()){
            InventoryListScreen(
                goAdd = {navController.navigate(InventoryGraph.inventoryCreate())},onOpenDrawer = onOpenDrawer, viewModel = inventorylistviewmodel
            )
        }
    }
}