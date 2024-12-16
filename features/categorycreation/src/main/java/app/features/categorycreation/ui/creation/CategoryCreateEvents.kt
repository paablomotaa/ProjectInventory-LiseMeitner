package app.features.categorycreation.ui.creation

import androidx.navigation.NavController
import app.domain.invoicing.category.CategoryType

data class CategoryCreateEvents(
    val onNameChange: (String) -> Unit,
    val onShortNameChange: (String) -> Unit,
    val onDescriptionChange: (String) -> Unit,
    val onTypeChange: (CategoryType) -> Unit,
    val onFungibleChange: (Boolean) -> Unit,
    val onCreationClick: (NavController) -> Unit
)