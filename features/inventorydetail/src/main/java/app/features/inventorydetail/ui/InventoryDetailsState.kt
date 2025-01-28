package app.features.inventorydetail.ui

import app.domain.invoicing.inventory.Inventory

sealed class InventoryDetailsState {
    data object NoData: InventoryDetailsState()
    data object Loading: InventoryDetailsState()
    data class Success(var inventory: Inventory): InventoryDetailsState()
}