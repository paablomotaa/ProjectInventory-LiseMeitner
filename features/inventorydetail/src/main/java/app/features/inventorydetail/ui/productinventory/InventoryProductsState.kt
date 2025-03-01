package app.features.inventorydetail.ui.productinventory

import app.domain.invoicing.product.Product

sealed class InventoryProductsState(){
    data object NoData: InventoryProductsState()
    data object Loading: InventoryProductsState()
    data class Succes(var data:List<Product>):InventoryProductsState()
}
