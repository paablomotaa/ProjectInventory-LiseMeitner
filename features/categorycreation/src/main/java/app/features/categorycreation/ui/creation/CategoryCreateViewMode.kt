package app.features.categorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.BaseResult
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.domain.invoicing.model.category.CategoryRepository
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
        } else {
            state = state.copy(name = name, isNameError = false)
        }
    }

    fun onShortNameChange(shortName: String) {
        if (shortName.contains(' ')) return
        if (shortName.isEmpty() || shortName.length < 3) {
            state = state.copy(shortName = shortName, errorShortNameFormat = "ERROR. Nombre corto incorrecto", isShortNameError = true)
        } else {
            state = state.copy(shortName = shortName, isShortNameError = false)
        }
    }

    fun onDescriptionChange(description: String) {
        if (description.contains(' ')) return
        if (description.isEmpty()) {
            state = state.copy(description = description, errorDescriptionFormat = "ERROR. Campo vacío", isDescriptionError = true)
        } else {
            state = state.copy(description = description, isDescriptionError = false)
        }
    }

    fun onImageUrlChange(imageUrl: String) {
        if (imageUrl.isEmpty() || !isValidUrl(imageUrl)) {
            state = state.copy(imageUrl = imageUrl, errorImageUrlFormat = "ERROR. URL no válida", isImageUrlError = true)
        } else {
            state = state.copy(imageUrl = imageUrl, isImageUrlError = false)
        }
    }

    fun onTypeChange(type: CategoryType) {
        state = state.copy(type = type)
    }

    fun onFungibleChange(isFungible: Boolean) {
        state = state.copy(isFungible = isFungible)
    }

    fun onCreationClick() {
        if (isEmptyFields()) {
            state = state.copy(isEmpty = "Algunos campos están vacíos")
            return
        }
        if (isErrorFields()) {
            return
        }
        viewModelScope.launch {
            // Llamada al repositorio para verificar duplicados o crear la categoría
            val response = CategoryRepository.isDuplicate(state.name)
            when (response) {
                is BaseResult.Error -> {
                    state = state.copy(isNameError = true, errorNameFormat = "Ya existe una categoría con ese nombre")
                }
                is BaseResult.Success -> {
                    CategoryRepository.addCategory(
                        Category(
                            id = UUID.randomUUID().toString(),
                            name = state.name,
                            shortName = state.shortName,
                            description = state.description,
                            imageUrl = state.imageUrl,
                            createdDate = Date(),
                            type = state.type,
                            isFungible = state.isFungible
                        )
                    )
                    state = state.copy(isSuccess = true)
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

        if (state.description.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isDescriptionError = true, errorDescriptionFormat = "La descripción no puede estar vacía")
        }

        if (state.shortName.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isShortNameError = true, errorShortNameFormat = "El nombre corto no puede estar vacío")
        }

        if (state.imageUrl.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isImageUrlError = true, errorImageUrlFormat = "La URL de la imagen no puede estar vacía")
        }

        return hasEmptyFields
    }

    private fun isErrorFields(): Boolean {
        return state.isNameError || state.isDescriptionError || state.isShortNameError || state.isImageUrlError
    }

    private fun isValidUrl(url: String): Boolean {
        val regex = "^(https?|ftp)://[^\\s/$.?#].[\\S]*$"
        return url.matches(regex.toRegex())
    }
}