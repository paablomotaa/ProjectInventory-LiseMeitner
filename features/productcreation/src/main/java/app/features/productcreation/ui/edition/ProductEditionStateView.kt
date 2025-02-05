package app.features.productcreation.ui.edition

import app.domain.invoicing.product.Product

sealed class ProductEditionStateView{
    data object Loading: ProductEditionStateView()
    data object Success: ProductEditionStateView()
}