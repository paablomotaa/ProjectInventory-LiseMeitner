package app.features.categorycreation.ui.creation

import android.content.Context
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

    // Funciones para actualizar el estado al cambiar los campos de entrada
    fun onNameChange(name: String, context: Context) {
        val error = CategoryCreationValidate.validateName(name, context)
        state = state.copy(
            name = name,
            isNameError = error != null,
            errorNameFormat = error?.let { context.getString(it) }
        )
    }

    fun onShortNameChange(shortName: String, context: Context) {
        val error = CategoryCreationValidate.validateShortName(shortName, context)
        state = state.copy(
            shortName = shortName,
            isShortNameError = error != null,
            errorShortNameFormat = error?.let { context.getString(it) }
        )
    }

    fun onDescriptionChange(description: String, context: Context) {
        val error = CategoryCreationValidate.validateDescription(description, context)
        state = state.copy(
            description = description,
            isDescriptionError = error != null,
            errorDescriptionFormat = error?.let { context.getString(it) }
        )
    }

    fun onImageUrlChange(imageUrl: String, context: Context) {
        val error = CategoryCreationValidate.validateImageUrl(imageUrl, context)
        state = state.copy(
            imageUrl = imageUrl,
            isImageUrlError = error != null,
            errorImageUrlFormat = error?.let { context.getString(it) }
        )
    }

    fun onTypeChange(type: CategoryType) {
        state = state.copy(type = type)
    }

    fun onFungibleChange(isFungible: Boolean) {
        state = state.copy(isFungible = isFungible)
    }

    // Función para manejar el clic del botón de creación de categoría
    fun onCreationClick(context: Context) {
        // Validar los campos
        val nameError = CategoryCreationValidate.validateName(state.name, context)
        val shortNameError = CategoryCreationValidate.validateShortName(state.shortName, context)
        val descriptionError = CategoryCreationValidate.validateDescription(state.description, context)
        val imageUrlError = CategoryCreationValidate.validateImageUrl(state.imageUrl, context)

        // Actualizar el estado con los errores
        state = state.copy(
            isNameError = nameError != null,
            errorNameFormat = nameError?.let { context.getString(it) },
            isShortNameError = shortNameError != null,
            errorShortNameFormat = shortNameError?.let { context.getString(it) },
            isDescriptionError = descriptionError != null,
            errorDescriptionFormat = descriptionError?.let { context.getString(it) },
            isImageUrlError = imageUrlError != null,
            errorImageUrlFormat = imageUrlError?.let { context.getString(it) }
        )

        // Si hay errores, no proceder
        if (nameError != null || shortNameError != null || descriptionError != null || imageUrlError != null) return

        // Si no hay errores, proceder con la creación
        viewModelScope.launch {
            val response = CategoryRepository.isDuplicate(state.name)
            when (response) {
                is BaseResult.Error -> {
                    state = state.copy(isNameError = true, errorNameFormat = "error_duplicate_name")
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
}