package com.example.inventory.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.accountsettings.ui.AccountListViewModel
import app.features.accountsettings.ui.AccountsListScreen

object AccountGraph {
    const val ROUTE = "account_graph"

    fun accountList() ="$ROUTE/accountList"
}

fun NavGraphBuilder.accountGraph(navController: NavController) {

    navigation(startDestination = AccountGraph.accountList(), route = InventoryGraph.ROUTE) {
        accountList(navController)
    }
}


private fun NavGraphBuilder.accountList(navController: NavController) {
    composable(route = AccountGraph.accountList()) {
        val viewModel = hiltViewModel<AccountListViewModel>()
        AccountsListScreen(
            viewModel, openDrawer = { /*TODO*/ },
            goToCreation = { /*TODO*/ },
            goToDetail = { /*TODO*/ })
    }
}