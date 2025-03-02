package com.example.inventory.home

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.domain.navigation.categoryGraph
import app.features.accountsignin.ui.LoginViewModel
import app.features.accountsignin.ui.SignUpGraph
import app.features.accountsignin.ui.signUpGraph
import app.features.categorycreation.ui.creation.CategoryCreateViewModel
import app.features.categorylist.ui.CategoryListViewModel
import com.example.inventory.navigation.InventoryGraph
import com.example.inventory.navigation.InventoryGraph.inventoryGraph
import com.example.inventory.navigation.accountGraph
import com.example.inventory.navigation.productGraph
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    categoryListViewModel: CategoryListViewModel,
    categoryCreateViewModel: CategoryCreateViewModel,
    onOpenDrawer: () -> Unit,
    viewModel: MainViewModel
) {
    viewModel.getName()
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.POST_NOTIFICATIONS,
        )
    )
    LaunchedEffect(Unit){
        snapshotFlow { viewModel.state }.collect{ state -> //Viene del cambio de estado del ViewModel(asincrono, flujo caliente)
            val destination = state.starNavDestination()
            if(navController.currentDestination?.route != destination)
                navController.navigate(destination)
        }
        if(!permissionsState.allPermissionsGranted)
            permissionsState.launchMultiplePermissionRequest()
    }
    NavHost(navController = navController, startDestination = SignUpGraph.ROUTE) {
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
        signUpGraph(
            navController
        )
    }
}