package app.features.productlist.ui

import app.domain.invoicing.product.Product

sealed class ProductListState(var expanded: Boolean = false){
    data object NoData: ProductListState()
    data object Loading: ProductListState()
    data class Success(var datalist: List<Product>): ProductListState()
}