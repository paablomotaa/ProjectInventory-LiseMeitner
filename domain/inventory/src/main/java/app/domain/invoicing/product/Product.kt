package app.domain.invoicing.product

import app.base.utils.Status
import app.domain.invoicing.category.Category
import java.util.Date

data class Product(
    val id: Long,
    val code: String,
    val name: String,
    val shortName: String,
    val description: String,
    val numSerial: Double,
    val codModel: String,
    val typeProduct: String,
    val category: Category,
    val section: String,
    val status: Status,
    val amount: Int,
    val price: Double,
    val image: String,
    val acquisitionDate: Date,
    val cancellationDate: Date,
    val notes: String,
    val tags: String
)