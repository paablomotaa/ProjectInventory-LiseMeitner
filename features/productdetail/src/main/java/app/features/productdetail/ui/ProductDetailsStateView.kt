package app.features.productdetail.ui

import app.domain.invoicing.product.Product

sealed class ProductDetailsStateView{
    data object Loading: ProductDetailsStateView()
    data object Success: ProductDetailsStateView()
}