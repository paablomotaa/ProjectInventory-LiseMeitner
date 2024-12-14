package app.features.inventorylist.ui

import app.domain.invoicing.inventory.Inventory

sealed class InventoryListState(var expanded:Boolean = false){
    data object NoData: InventoryListState()
    data object Loading: InventoryListState()
    data class Succes(var data:List<Inventory>):InventoryListState()
}