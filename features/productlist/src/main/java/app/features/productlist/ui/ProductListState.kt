package app.features.productlist.ui

import app.domain.invoicing.product.Product

sealed class ProductListState{
    data object NoData: ProductListState()
    data object Loading: ProductListState()
    data class Success(var datalist: List<Product>): ProductListState()
}