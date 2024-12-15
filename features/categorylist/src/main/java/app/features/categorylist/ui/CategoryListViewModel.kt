package app.features.categorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.category.Category
import app.domain.invoicing.model.category.CategoryRepository
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {
    var state by mutableStateOf(CategoryListState())
        private set

    init {
        fetchCategories()
    }

    // Obtener categorías desde el repositorio
    fun fetchCategories() {
        state = state.copy(isLoading = true, isError = false, isEmpty = false)
        viewModelScope.launch {
            try {
                // Aseguramos que el repositorio esté devolviendo correctamente los datos
                CategoryRepository.getAllCategories().collect { categories ->
                    if (categories.isEmpty()) {
                        // Si no hay categorías, lo manejamos así
                        state = state.copy(
                            categories = categories,
                            isLoading = false,
                            isEmpty = true,
                            errorMessage = "No categories available"
                        )
                    } else {
                        // Si hay categorías, actualizamos el estado sin error
                        state = state.copy(
                            categories = categories,
                            isLoading = false,
                            isEmpty = false,
                            errorMessage = "" // Limpiamos cualquier mensaje de error previo
                        )
                    }
                }
            } catch (e: Exception) {
                // En caso de error, actualizamos el estado para reflejarlo
                state = state.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = "Error loading categories"
                )
                println("Error fetching categories: ${e.message}")
            }
        }
    }


    // Eliminar una categoría
    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            try {
                CategoryRepository.deleteCategory(category)
                fetchCategories() // Refresca la lista después de eliminar
            } catch (e: Exception) {
                state = state.copy(
                    isError = true,
                    errorMessage = "Error deleting category"
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