package app.features.categorycreation.ui.creation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import app.base.utils.BaseResult
import app.base.utils.BaseResult.Success
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.domain.ddd.repository.CategoryRepository
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class CategoryCreateViewModel : ViewModel() {
    var state by mutableStateOf(CategoryCreateState())
        private set

    fun onNameChange(name: String) {
        if (name.contains(' ')) return
        if (name.isEmpty()) {
            state = state.copy(name = name, isNameError = true, errorNameFormat = "ERROR. Campo vacío")
            return
        } else {
            state = state.copy(name = name, isNameError = false, errorNameFormat = "")
        }
    }

    fun onShortNameChange(shortName: String) {
        if (shortName.contains(' ')) return
        if (!isValidShortName(shortName) || shortName.length < 3) {
            state = state.copy(shortName = shortName, isShortNameError = true, errorShortNameFormat = "ERROR. Formato mal puesto")
            return
        } else {
            state = state.copy(shortName = shortName, isShortNameError = false, errorShortNameFormat = "")
        }
    }

    fun onDescriptionChange(description: String) {
        if (description.contains(' ')) return
        if (description.isEmpty()) {
            state = state.copy(description = description, isDescriptionError = true, errorDescriptionFormat = "ERROR. Campo vacío")
            return
        } else {
            state = state.copy(description = description, isDescriptionError = false, errorDescriptionFormat = "")
        }
    }

    fun onImageUrlChange(imageUrl: String) {
        if (imageUrl.isEmpty()) {
            state = state.copy(imageUrl = imageUrl, isImageUrlError = true, errorImageUrlFormat = "ERROR. URL vacía")
            return
        } else {
            state = state.copy(imageUrl = imageUrl, isImageUrlError = false, errorImageUrlFormat = "")
        }
    }

    fun onTypeChange(type: CategoryType) {
        state = state.copy(type = type)
    }

    fun onFungibleChange(isFungible: Boolean) {
        state = state.copy(isFungible = isFungible)
    }

    fun onCreationClick(navController: NavController) {
        if (isEmptyFields()) {
            return
        }
        if (isErrorFields()) {
            return
        }

        viewModelScope.launch {
            val response = CategoryRepository.isDuplicate(state.name)
            when (response) {
                is BaseResult.Error -> {
                    state = state.copy(isNameError = true, errorNameFormat = "Categoría duplicada")
                    return@launch
                }
                is Success -> {
                    val category = Category(
                        id = UUID.randomUUID().toString(),
                        name = state.name,
                        shortName = state.shortName,
                        description = state.description,
                        imageUrl = state.imageUrl,
                        createdDate = Date(),
                        type = state.type,
                        isFungible = state.isFungible
                    )
                    CategoryRepository.addCategory(category)
                    state = state.copy(isSuccess = true)
                    navController.popBackStack()
                    CategoryRepository.getAllCategories()
                }
            }
        }
    }

    private fun isEmptyFields(): Boolean {
        var hasEmptyFields = false

        if (state.name.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isNameError = true, errorNameFormat = "El nombre no puede estar vacío")
        }

        if (state.shortName.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isShortNameError = true, errorShortNameFormat = "El nombre corto no puede estar vacío")
        }

        if (state.description.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isDescriptionError = true, errorDescriptionFormat = "La descripción no puede estar vacía")
        }

        if (state.imageUrl.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isImageUrlError = true, errorImageUrlFormat = "La URL de la imagen no puede estar vacía")
        }

        return hasEmptyFields
    }

    private fun isErrorFields(): Boolean {
        return state.isNameError || state.isShortNameError || state.isDescriptionError || state.isImageUrlError
    }

    private fun isValidShortName(shortName: String): Boolean {
        return shortName.length >= 3
    }
}