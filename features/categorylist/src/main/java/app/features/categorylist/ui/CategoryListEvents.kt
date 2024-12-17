package app.features.categorylist.ui

import androidx.navigation.NavController
import app.domain.invoicing.category.Category

data class CategoryListEvents(
    val onCategoryClick: (Category) -> Unit,
    val onFabClick: () -> Unit,
    val onDeleteClick: (Category) -> Unit,
    val onEditClick: (Category) -> Unit,
    val onRetry: () -> Unit
)