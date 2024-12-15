package com.example.inventory.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.features.categorycreation.ui.creation.CategoryCreateViewModel
import app.features.categorycreation.ui.creation.CategoryFormScreen
import app.features.categorycreation.ui.edition.UpdateCategoryScreen
import app.features.categorydetail.ui.CategoryDetailsScreen
import app.features.categorylist.ui.CategoryListScreen
import app.features.categorylist.ui.CategoryListViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "categoryList") {
        // Define las rutas de navegación
        composable("categoryList") {
            // Aquí llamas a la pantalla de lista de categorías
            CategoryListScreen(
                viewModel = CategoryListViewModel(), // Aquí pasas el ViewModel
                navController = navController
            )
        }

        composable("categoryCreate") {
            CategoryFormScreen(
                viewModel = CategoryCreateViewModel(),
                onSave = { navController.popBackStack() },
                navController = navController
            )
        }
    }
}
