package app.features.categorycreation.ui.creation

import android.content.Context
import app.features.categorycreation.R

object CategoryCreationValidate {
    // Validación de nombre
    fun validateName(name: String, context: Context): Int? {
        return when {
            name.isEmpty() -> R.string.error_field_empty
            name.contains(' ') -> R.string.error_name_format
            else -> null // No hay error
        }
    }

    // Validación de short name
    fun validateShortName(shortName: String, context: Context): Int? {
        return when {
            shortName.isEmpty() -> R.string.error_short_name
            shortName.length != 3 -> R.string.error_short_name_format
            shortName.contains(' ') -> R.string.error_short_name_format
            else -> null // No hay error
        }
    }

    // Validación de descripción
    fun validateDescription(description: String, context: Context): Int? {
        return if (description.isEmpty()) {
            R.string.error_description // Error: descripción vacía
        } else {
            null // No hay error
        }
    }

    // Validación de URL de imagen
    fun validateImageUrl(imageUrl: String, context: Context): Int? {
        return when {
            imageUrl.isEmpty() -> R.string.error_field_empty
            !isValidUrl(imageUrl) -> R.string.error_image_url_format
            else -> null // No hay error
        }
    }

    private fun isValidUrl(url: String): Boolean {
        val regex = "^(https?|ftp)://[^\\s/$.?#].[\\S]*$"
        return url.matches(regex.toRegex())
    }
}