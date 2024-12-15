package app.features.categorycreation.ui.creation

import app.domain.invoicing.category.CategoryType
import java.time.LocalDate
import java.util.Date

data class CategoryCreateState(

    val id: String = "",
    val name: String = "",
    val shortName: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val createdDate: Date = Date.from(LocalDate.now().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()),
    val type: CategoryType = CategoryType.BASICO,
    val isFungible: Boolean = false,


    val isNameError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isShortNameError: Boolean = false,
    val isErrorCreation: Boolean = false,
    val isImageUrlError: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isNoData: Boolean = false,


    val errorNameFormat: String = "",
    val errorShortNameFormat: String = "",
    val errorDescriptionFormat: String = "",
    val errorImageUrlFormat: String = "",
    val isEmpty: String = "",
)
// Verificar que los campos sean correctos
fun CategoryCreateState.isValidForm(): Boolean {
    return name.isNotEmpty() &&
            shortName.isNotEmpty() &&
            description.isNotEmpty() &&
            !isNameError &&
            !isShortNameError &&
            !isDescriptionError &&
            !isImageUrlError &&
            !isErrorCreation
}