package app.features.categorylist.ui

import app.domain.invoicing.category.Category

sealed class CategoryListState(var expanded: Boolean = false) {
    data object NoData : CategoryListState()
    data object Loading : CategoryListState()
    data class Success(val categories: List<Category>) : CategoryListState()
    data class Error(val errorMessage: String) : CategoryListState()
}