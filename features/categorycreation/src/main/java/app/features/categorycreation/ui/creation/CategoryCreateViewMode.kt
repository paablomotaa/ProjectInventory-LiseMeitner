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

    fun onCreationClick(context: Context) {
        // Validar los campos
        val errors = listOf(
            CategoryCreationValidate.validateName(state.name, context),
            CategoryCreationValidate.validateShortName(state.shortName, context),
            CategoryCreationValidate.validateDescription(state.description, context),
            CategoryCreationValidate.validateImageUrl(state.imageUrl, context)
        )

        // Asignar los errores de forma más compacta
        state = state.copy(
            isNameError = errors[0] != null,
            errorNameFormat = errors[0]?.let { context.getString(it) },
            isShortNameError = errors[1] != null,
            errorShortNameFormat = errors[1]?.let { context.getString(it) },
            isDescriptionError = errors[2] != null,
            errorDescriptionFormat = errors[2]?.let { context.getString(it) },
            isImageUrlError = errors[3] != null,
            errorImageUrlFormat = errors[3]?.let { context.getString(it) }
        )

        if (errors.any { it != null }) return

        // Si no hay errores, proceder con la creación
        viewModelScope.launch {
            // Llamada al repositorio para verificar duplicados o crear la categoría
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

    private fun isEmptyFields(context: Context): Boolean {
        var hasEmptyFields = false

        val nameError = CategoryCreationValidate.validateName(state.name, context)
        if (nameError != null) {
            hasEmptyFields = true
            state = state.copy(isNameError = true, errorNameFormat = context.getString(nameError))
        }

        val descriptionError = CategoryCreationValidate.validateDescription(state.description, context)
        if (descriptionError != null) {
            hasEmptyFields = true
            state = state.copy(isDescriptionError = true, errorDescriptionFormat = context.getString(descriptionError))
        }

        val shortNameError = CategoryCreationValidate.validateShortName(state.shortName, context)
        if (shortNameError != null) {
            hasEmptyFields = true
            state = state.copy(isShortNameError = true, errorShortNameFormat = context.getString(shortNameError))
        }

        val imageUrlError = CategoryCreationValidate.validateImageUrl(state.imageUrl, context)
        if (imageUrlError != null) {
            hasEmptyFields = true
            state = state.copy(isImageUrlError = true, errorImageUrlFormat = context.getString(imageUrlError))
        }

        return hasEmptyFields
    }

    private fun isErrorFields(): Boolean {
        return state.isNameError || state.isDescriptionError || state.isShortNameError || state.isImageUrlError
    }

    fun validateUrl(imageUrl: String, context: Context): Int? {
        return CategoryCreationValidate.validateImageUrl(imageUrl, context)
    }
}