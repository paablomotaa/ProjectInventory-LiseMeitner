package app.domain.invoicing.category

import java.util.Date

data class Category(
    val id: String,
    val name: String,
    val shortName: String,
    val description: String,
    val imageUrl: String,
    val createdDate: Date,
    val type: CategoryType = CategoryType.BASICO,
    val isFungible: Boolean = false
)

enum class CategoryType {
    BASICO, ECONOMICO, ECOLOGICO, PREMIUM
}