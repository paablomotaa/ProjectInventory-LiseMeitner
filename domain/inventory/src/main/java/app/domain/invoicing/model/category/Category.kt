package app.domain.invoicing.category

import java.util.Date

data class Category(
    val id: String,
    var name: String,
    var shortName: String,
    var description: String,
    var imageUrl: String,
    val createdDate: Date,
    var type: CategoryType = CategoryType.BASICO,
    var isFungible: Boolean = false
)

enum class CategoryType {
    BASICO, ECONOMICO, ECOLOGICO, PREMIUM
}