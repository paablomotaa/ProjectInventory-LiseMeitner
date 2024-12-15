package app.features.productcreation.ui.creation

import app.domain.invoicing.category.Category
import app.domain.invoicing.section.Section
import app.base.utils.Status
import java.time.LocalDate

data class ProductCreationState(
    val id: Long = 0,
    val code: String = "",
    val name: String = "",
    val shortName: String = "",
    val description: String = "",
    val numSerial: Double = 0.0,
    val codModel: String = "",
    val typeProduct: String = "",
    val category: Category? = null,
    val section: String = "",
    val status: Status = Status.NEW,
    val amount: Int = 0,
    val price: Double = 0.0,
    val image: String = "",
    val acquisitionDate: LocalDate = LocalDate.now(),
    val cancellationDate: LocalDate = LocalDate.now(),
    val notes: String = "",
    val tags: String = "",

    val expandedTipoState: Boolean = false,
    val expandedCategoriaState: Boolean = false,
    val expandedSeccionState: Boolean = false,
    val expandedEstadoState: Boolean = false,
    val showDialogAcquisition: Boolean = false,
    val showDialogCancellation: Boolean = false,

    val listTipoProducto: List<String> = emptyList(),
    val listCategoria: List<Category> = emptyList(),
    val listSeccion: List<String> = emptyList(),
    val listStatus: List<Status> = emptyList(),

    //region Errors
    val codeError: Boolean = false,
    val codeFormatError: String? = null,

    val nameError: Boolean = false,
    val nameFormatError: String? = null,

    val shortNameError: Boolean = false,
    val shortNameFormatError: String? = null,

    val descriptionError: Boolean = false,
    val descriptionFormatError: String? = null,

    val numSerialError: Boolean = false,
    val numSerialFormatError: String? = null,

    val codModelError: Boolean = false,
    val codModelFormatError: String? = null,

    val typeProductError: Boolean = false,
    val typeProductFormatError: String? = null,
    val categoryError: Boolean = false,
    val categoryFormatError: String? = null,
    val sectionError: Boolean = false,
    val sectionFormatError: String? = null,

    val statusError: Boolean = false,
    val statusFormatError: String? = null,

    val amountError: Boolean = false,
    val amountFormatError: String? = null,

    val priceError: Boolean = false,
    val priceFormatError: String? = null,

    val imageError: Boolean = false,
    val imageFormatError: String? = null,

    val acquisitionDateError: Boolean = false,
    val acquisitionDateFormatError: String? = null,
    val cancellationDateError: Boolean = false,
    val cancellationDateFormatError: String? = null,

    val notesError: Boolean = false,
    val notesFormatError: String? = null,

    val tagsError: Boolean = false,
    val tagsFormatError: String? = null,
    //endregion

    val isError:Boolean = false,
    val isExitsError:Boolean = false,
    val isEmpty:Boolean = false,
    val isOffline: Boolean = false,
    var isLoading: Boolean = false,
    var success: Boolean = false,

    )