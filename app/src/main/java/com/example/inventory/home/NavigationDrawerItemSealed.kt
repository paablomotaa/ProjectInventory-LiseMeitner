package com.example.inventory.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import app.domain.navigation.CategoryGraph
import com.example.inventory.R
import com.example.inventory.navigation.InventoryGraph
import com.example.inventory.navigation.ProductGraph

//TODO("Cambiar los iconos")
sealed class NavigationDrawerItemSealed (val route: String, val icon: ImageVector, val label: Int) {
    data object Inventory: NavigationDrawerItemSealed(InventoryGraph.ROUTE, Icons.Default.AccountBox, R.string.inventory)
    data object Product: NavigationDrawerItemSealed(ProductGraph.ROUTE , Icons.Default.ShoppingCart,R.string.product)
    data object Category: NavigationDrawerItemSealed(CategoryGraph.ROUTE, Icons.Default.Info, R.string.category)

    companion object{
        fun list() = listOf(Inventory,Product,Category)
    }
}