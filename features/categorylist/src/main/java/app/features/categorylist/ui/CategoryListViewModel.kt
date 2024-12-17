package app.features.categorylist.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.category.Category
import app.domain.ddd.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {

    var state by mutableStateOf<CategoryListState>(CategoryListState.Loading)
        private set

    var categoryList: List<Category> by mutableStateOf(emptyList())
        private set

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            state = CategoryListState.Loading
            CategoryRepository.getAllCategories().collect { categories ->
                if (categories.isNotEmpty()) {
                    categoryList = categories
                    state = CategoryListState.Success(categoryList)
                } else {
                    state = CategoryListState.NoData
                }
            }
        }

    }

    fun onDeleteCategory(category: Category) {
        viewModelScope.launch {
            try {
                CategoryRepository.deleteCategory(category)
                fetchCategories()
            } catch (e: Exception) {
                state = CategoryListState.Error("Error deleting category")
            }
        }
    }
}