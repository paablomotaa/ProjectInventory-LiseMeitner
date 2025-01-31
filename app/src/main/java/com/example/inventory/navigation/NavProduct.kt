package com.example.inventory.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.domain.invoicing.product.Product
import app.features.productcreation.ui.creation.ProductCreationScreen
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionScreen
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailsScreen
import app.features.productdetail.ui.ProductDetailsStateView
import app.features.productdetail.ui.ProductDetailsViewModel
import app.features.productlist.ui.ProductListScreen
import app.features.productlist.ui.ProductListViewModel

object ProductGraph {
    const val ROUTE = "account_graph"

    fun productCreate() = "$ROUTE/productCreate"
    fun productList() = "$ROUTE/productList"
    fun productView() = "$ROUTE/productView/{idProduct}"
    fun productEdit() = "$ROUTE/productEdit/{idProduct}"
}

fun NavGraphBuilder.productGraph(
    navController: NavController,
    onOpenDrawer: () -> Unit
) {

    navigation(startDestination = ProductGraph.productList(), route = ProductGraph.ROUTE) {
        productCreate(navController)
        productList(navController, onOpenDrawer)
        productView(navController)
        productEdit(navController)
    }
}

private fun NavGraphBuilder.productCreate(navController: NavController) {
    composable(route = ProductGraph.productCreate()) {

        val productCreationViewModel = hiltViewModel<ProductCreationViewModel>()
        productCreationViewModel.reset()
        productCreationViewModel.getList()
        ProductCreationScreen(
            goBack = {navController.popBackStack()},
            viewModel = productCreationViewModel
        )
    }
}

private fun NavGraphBuilder.productList(
    navController: NavController,
    onOpenDrawer: () -> Unit
) {
    composable(route = ProductGraph.productList()) {
        val productListViewModel = hiltViewModel<ProductListViewModel>()
        productListViewModel.getList()
        ProductListScreen(
            goBack = {navController.popBackStack()},
            goAdd = {navController.navigate(ProductGraph.productCreate())},
            goView = {navController.navigate(ProductGraph.productView().replace("{idProduct}", productListViewModel.idProduct.toString()))},
            productListViewModel,
            onOpenDrawer
        )
    }
}

private fun NavGraphBuilder.productEdit(navController: NavController) {
    composable(
        route = ProductGraph.productEdit(),
        arguments = listOf(navArgument("idProduct") { type = NavType.StringType })) { backStackEntry ->
        val idProduct = backStackEntry.arguments?.getString("idProduct") ?: ""

        val productEditViewModel = hiltViewModel<ProductEditionViewModel>()
        productEditViewModel.importProduct(idProduct.toLong())
        productEditViewModel.getList()
        ProductEditionScreen(
            goBack = {navController.popBackStack()},
            accept = {navController.navigate(ProductGraph.productList())},
            productEditViewModel
        )
    }
}

private fun NavGraphBuilder.productView(navController: NavController) {
    composable(
        route = ProductGraph.productView(),
        arguments = listOf(navArgument("idProduct") { type = NavType.StringType })) { backStackEntry ->
        val idProduct = backStackEntry.arguments?.getString("idProduct") ?: ""
        val productDetailsViewModel = hiltViewModel<ProductDetailsViewModel>()
        productDetailsViewModel.importProduct(idProduct.toLong())
        ProductDetailsScreen(
            goBack = {navController.popBackStack()},
            goEdit = {navController.navigate(ProductGraph.productEdit().replace("{idProduct}", productDetailsViewModel.idProduct.toString()))},
            productDetailsViewModel
        )
    }
}