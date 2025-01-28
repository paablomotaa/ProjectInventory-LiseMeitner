package com.example.inventory.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.domain.navigation.categoryGraph
import app.features.categorycreation.ui.creation.CategoryCreateViewModel
import app.features.categorylist.ui.CategoryListViewModel
import app.features.inventorycreation.ui.creation.InventoryCreationViewModel
import app.features.inventorydetail.ui.InventoryDetailsViewModel
import app.features.inventorylist.ui.InventoryListViewModel
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailsViewModel
import app.features.productlist.ui.ProductListViewModel
import com.example.inventory.navigation.InventoryGraph
import com.example.inventory.navigation.InventoryGraph.inventoryGraph
import com.example.inventory.navigation.productGraph

@Composable
fun HomeScreen(
    navController: NavHostController,
    inventoryListViewModel: InventoryListViewModel,
    inventoryCreationViewModel: InventoryCreationViewModel,
    inventoryDetailsViewModel: InventoryDetailsViewModel,

    productListViewModel: ProductListViewModel,
    productCreationViewModel: ProductCreationViewModel,
    productEditionViewModel: ProductEditionViewModel,
    productDetailsViewModel: ProductDetailsViewModel,

    categoryListViewModel: CategoryListViewModel,
    categoryCreateViewModel: CategoryCreateViewModel,
    onOpenDrawer: () -> Unit
) {
    NavHost(navController = navController, startDestination = InventoryGraph.ROUTE) {
        inventoryGraph(
            navController,
            inventoryListViewModel,
            inventoryCreationViewModel,
            inventoryDetailsViewModel,
            onOpenDrawer
        )
        productGraph(
            navController,
            productCreationViewModel,
            productListViewModel,
            productEditionViewModel,
            productDetailsViewModel,
            onOpenDrawer
        )
        categoryGraph(
            navController,
            categoryListViewModel,
            categoryCreateViewModel,
            onOpenDrawer
        )
    }
}