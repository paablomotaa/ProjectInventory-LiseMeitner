package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import app.features.inventorycreation.ui.creation.InventoryCreationViewModel
import app.features.inventorylist.ui.InventoryListViewModel
import com.example.inventory.home.HomeScreen
import com.example.inventory.theme.InventoryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val inventoryListViewModel: InventoryListViewModel by viewModels()
    val inventoryCreationViewModel: InventoryCreationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            InventoryTheme {
                Surface {
                    HomeScreen(navController = navController,inventoryListViewModel,inventoryCreationViewModel )
                }
            }
        }
    }
}