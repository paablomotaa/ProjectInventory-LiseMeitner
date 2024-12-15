package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailsViewModel
import app.features.productlist.ui.ProductListViewModel
import com.example.inventory.home.HomeScreen
import com.example.inventory.theme.InventoryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val productListViewModel: ProductListViewModel by viewModels()
    val productCreationViewModel: ProductCreationViewModel by viewModels()
    val productDetailsViewModel: ProductDetailsViewModel by viewModels()
    val productEditViewModel: ProductEditionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            InventoryTheme {
                Surface {
                    HomeScreen(navController = navController, productListViewModel, productCreationViewModel, productDetailsViewModel, productEditViewModel)
                }
            }
        }
    }
}