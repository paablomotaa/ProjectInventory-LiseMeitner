package app.features.inventorydetail.ui

import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.Flow

sealed class InventoryDetailsState {
    data object NoData: InventoryDetailsState()
    data object Loading: InventoryDetailsState()
    data class Success(var inventory: Inventory): InventoryDetailsState()

}