package com.example.inventory.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailsViewModel
import app.features.productlist.ui.ProductListViewModel
import com.example.inventory.navigation.ProductGraph
import com.example.inventory.navigation.productGraph

@Composable
fun HomeScreen(navController: NavHostController, productListViewModel: ProductListViewModel, productCreationViewModel: ProductCreationViewModel, productDetailsViewModel: ProductDetailsViewModel, productEditViewModel: ProductEditionViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ProductGraph.ROUTE) {

        productGraph(navController, productCreationViewModel, productListViewModel, productEditViewModel, productDetailsViewModel)
    }
}