package com.example.inventory

import AppDrawer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.features.categorycreation.ui.creation.CategoryCreateViewModel
import app.features.categorylist.ui.CategoryListViewModel
import app.features.inventorycreation.ui.creation.InventoryCreationViewModel
import app.features.inventorylist.ui.InventoryListViewModel
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailsViewModel
import app.features.productlist.ui.ProductListViewModel
import com.example.inventory.home.HomeScreen
import com.example.inventory.theme.InventoryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val inventoryListViewModel: InventoryListViewModel by viewModels()
    val inventoryCreationViewModel: InventoryCreationViewModel by viewModels()
    val categoryListViewModel: CategoryListViewModel by viewModels()
    val categoryCreateViewModel: CategoryCreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            InventoryTheme {
                Surface {
                    AppDrawer(navController = navController, navBackStackEntry = navBackStackEntry,drawerState = drawerState, scope = scope) {
                        HomeScreen(
                            navController = navController,
                            inventoryListViewModel,
                            inventoryCreationViewModel,
                            categoryListViewModel,
                            categoryCreateViewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } }
                        )
                    }
                }
            }
        }
    }
}