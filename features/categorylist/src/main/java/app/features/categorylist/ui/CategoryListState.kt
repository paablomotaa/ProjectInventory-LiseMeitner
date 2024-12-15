package app.features.categorylist.ui

import app.domain.invoicing.category.Category

data class CategoryListState(
    // Variables
    val categories: List<Category> = emptyList(),

    // Estados
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isEmpty: Boolean = false,
    val isSuccess: Boolean = false,
)