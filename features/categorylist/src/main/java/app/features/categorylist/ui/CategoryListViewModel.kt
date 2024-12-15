package app.features.categorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.category.Category
import app.domain.invoicing.model.category.CategoryRepository
import app.features.categorylist.R
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {
    var state by mutableStateOf(CategoryListState())
        private set

    init {
        fetchCategories()
    }

    // Obtener categorías desde el repositorio
    fun fetchCategories() {
        state = state.copy(isLoading = true, isError = false)
        viewModelScope.launch {
            try {
                CategoryRepository.getAllCategories().collect { categories ->
                    if (categories.isEmpty()) {
                        state = state.copy(
                            categories = categories,
                            isLoading = false,
                            isEmpty = true,
                            errorMessage = "empty"
                        )
                    } else {
                        state = state.copy(
                            categories = categories,
                            isLoading = false,
                            isEmpty = false
                        )
                    }
                }
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = "error_loading"
                )
            }
        }
    }

    // Eliminar una categoría
    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            try {
                CategoryRepository.deleteCategory(category)
                fetchCategories() // Actualizar la lista después de eliminar
            } catch (e: Exception) {
                state = state.copy(
                    isError = true,
                    errorMessage = "error_deleting_category"
                )
            }
        }
    }

    fun editCategory(category: Category) {
        //TODO crear la funcion para editar la categoria
    }

    fun clearError() {
        state = state.copy(isError = false, errorMessage = "")
    }
}