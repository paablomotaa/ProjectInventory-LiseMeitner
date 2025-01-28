package app.features.productdetail.ui

import app.base.utils.Status
import app.domain.invoicing.category.Category
import java.time.LocalDate

data class ProductDetailsState(
    val id: Long = 0,
    val code: String = "",
    val name: String = "",
    val shortName: String = "",
    val description: String = "",
    val numSerial: Double = 0.0,
    val codModel: String = "",
    val typeProduct: String = "",
    val category: String = "",
    val section: String = "",
    val status: Status = Status.NEW,
    val amount: Int = 0,
    val price: Double = 0.0,
    val image: String = "",
    val acquisitionDate: LocalDate = LocalDate.now(),
    val cancellationDate: LocalDate = LocalDate.now(),
    val notes: String = "",
    val tags: String = "",

    val isOffline: Boolean = false,
    var isLoading: Boolean = true,
    var success: Boolean = false
)