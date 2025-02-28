package com.example.inventory.home

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    categoryListViewModel: CategoryListViewModel,
    categoryCreateViewModel: CategoryCreateViewModel,
    onOpenDrawer: () -> Unit
) {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.POST_NOTIFICATIONS,
        )
    )
    LaunchedEffect(Unit){
        if(!permissionsState.allPermissionsGranted)
            permissionsState.launchMultiplePermissionRequest()
    }

    NavHost(navController = navController, startDestination = InventoryGraph.ROUTE) {
        inventoryGraph(
            navController,
            onOpenDrawer
        )
        productGraph(
            navController,
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