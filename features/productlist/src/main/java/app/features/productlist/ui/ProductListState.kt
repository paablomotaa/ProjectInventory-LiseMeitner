package app.features.productlist.ui

import app.domain.invoicing.product.Product

sealed class ProductListState(var expanded: Boolean = false, var tag: String = ""){
    data object NoData: ProductListState()
    data object Loading: ProductListState()
    data class Success(var datalist: List<Product>): ProductListState()
}