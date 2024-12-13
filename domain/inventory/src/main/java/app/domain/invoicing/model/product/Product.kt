package app.domain.invoicing.product

import app.base.utils.Status
import app.domain.invoicing.category.Category
import app.domain.invoicing.section.Section
import java.time.LocalDate

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
    val section: Section?,
    val status: Status,
    val amount: Int,
    val price: Double,
    val image: String,
    val acquisitionDate: LocalDate,
    val cancellationDate: LocalDate,
    val notes: String,
    val tags: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}