package app.features.productcreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.Status
import app.domain.ddd.repository.CategoryRepository
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


private const val especialExpresion = "*/%!?()[]{}=+-_\":;,.;:|&%#@~*^`\'"

@HiltViewModel
class ProductEditionViewModel @Inject constructor(private val provideProductRepository: ProductRepository) : ViewModel() {
    var state by mutableStateOf(ProductEditionState())
        private set

    var stateView by mutableStateOf<ProductEditionStateView>(ProductEditionStateView.Loading)

    var listCategory: List<Category> = emptyList()
        private set

    var listSection: List<String> = emptyList()
        private set

    fun importProduct(id: Long){
        viewModelScope.launch {
            stateView = ProductEditionStateView.Loading
            provideProductRepository.findProduct(id).collect{ product ->
                if(product != null){
                    state = state.copy(
                        id = product.id,
                        code = product.code,
                        name = product.name,
                        shortName = product.shortName,
                        description = product.description,
                        numSerial = product.numSerial,
                        codModel = product.codModel,
                        typeProduct = product.typeProduct,
                        category = product.category,
                        section = product.section,
                        status = product.status,
                        amount = product.amount,
                        price = product.price,
                        image = product.image,
                        acquisitionDate = product.acquisitionDate,
                        cancellationDate = product.cancellationDate,
                        tags = product.tags,
                        notes = product.notes
                    )
                }
            }
        }
    }

    fun getList(){
        viewModelScope.launch {
            provideProductRepository.getStatus().collect{ products ->
                if(products.isNotEmpty())
                    state = state.copy(listStatus = products)
            }
            CategoryRepository.getAllCategories().collect{ categories ->
                if(categories.isNotEmpty())
                    state = state.copy(listCategoria = categories)
            }
            stateView = ProductEditionStateView.Success
        }
    }

    //region onChange

    fun onCodeChange(code: String){
        if (code.contains(' '))return

        if (code.isEmpty())
            state = state.copy(
                code = code,
                codeError = true,
                codeFormatError = "Error de formato code"
            )
        else
            state =
                state.copy(code = code, codeError = false, codeFormatError = null)
    }

    fun onCodModelChange(codModel: String) {
        if (codModel.contains(' ') || codModel.contains(especialExpresion)) return


        if (codModel.isEmpty())
            state = state.copy(
                codModel = codModel,
                codModelError = true,
                codModelFormatError = "Error de formato codModel"
            )
        else
            state =
                state.copy(codModel = codModel, codModelError = false, codModelFormatError = null)
    }

    fun onNameChange(name: String) {
        if(name.isEmpty())
            state = state.copy(name = name, nameError = true, nameFormatError = "Error de formato name")
        else
            state = state.copy(name = name, nameError = false, nameFormatError = null)
    }

    fun onShortNameChange(shortName: String) {
        if (shortName.contains(' ') || shortName.contains(especialExpresion)) return

        if (shortName.count() < 3 || shortName.isEmpty())
            state = state.copy(
                shortName = shortName,
                shortNameError = true,
                shortNameFormatError = "El shortName debe tener al menos 3 caracteres"
            )
        else
            state = state.copy(
                shortName = shortName,
                shortNameError = false,
                shortNameFormatError = null
            )
    }

    fun onDescriptionChange(description: String) {
        if(description.isEmpty())
            state = state.copy(description = description, descriptionError = true, descriptionFormatError = "Error de formato description")
        else
            state = state.copy(description = description, descriptionError = false, descriptionFormatError = null)
    }

    fun onNumSerialChange(numSerial: Double?) {
        if (numSerial == null) return
        state = state.copy(numSerial = numSerial)
    }

    fun onTypeProductChange(typeProduct: String) {
        state = state.copy(typeProduct = typeProduct)
    }

    fun onCategoryChange(category: String) {
        state = state.copy(category = category)
    }

    fun onSectionChange(section: String) {
        state = state.copy(section = section)
    }

    fun onStatusChange(status: Status) {
        state = state.copy(status = status)
    }

    fun onAmountChange(amount: Int?) {
        if (amount == null) return
        if (amount < 1)
            state = state.copy(
                amount = amount,
                amountError = true,
                amountFormatError = "El amount debe ser igual o mayor a 1"
            )
        else
            state = state.copy(amount = amount, amountError = false, amountFormatError = null)

    }

    fun onPriceChange(price: Double?) {
        if (price == null) return
        if (price < 0.0)
            state = state.copy(
                price = price,
                priceError = true,
                priceFormatError = "El price debe ser 0.0 o superior"
            )
        else
            state = state.copy(price = price, priceError = false, priceFormatError = null)

    }

    fun onImageChange(image: String) {
        state = state.copy(image = image)
    }

    fun onAcquisitionDateChange(acquisitionDate: LocalDate) {
        state = state.copy(acquisitionDate = acquisitionDate)
    }

    fun onCancellationDateChange(cancellationDate: LocalDate) {
        if (cancellationDate < state.acquisitionDate)
            state = state.copy(
                cancellationDate = cancellationDate,
                cancellationDateError = true,
                cancellationDateFormatError = "La fecha de cancelación debe ser mayor a la de adquisición"
            )
        else
            state = state.copy(
                cancellationDate = cancellationDate,
                cancellationDateError = false,
                cancellationDateFormatError = null
            )
    }

    fun onNotesChange(notes: String) {
        state = state.copy(notes = notes)
    }

    fun onTagsChange(tags: String) {
        state = state.copy(tags = tags)
    }
    //endregion

    //region onExpanded
    fun onExpandedTipoState(expanded: Boolean) {
        state = state.copy(expandedTipoState = expanded)
    }

    fun onExpandedCategoriaState(expanded: Boolean) {
        state = state.copy(expandedCategoriaState = expanded)
    }

    fun onExpandedSeccionState(expanded: Boolean) {
        state = state.copy(expandedSeccionState = expanded)
    }

    fun onExpandedEstadoState(expanded: Boolean) {
        state = state.copy(expandedEstadoState = expanded)
    }
    //endregion

    //region onShow
    fun onShowDialogAcquisitionDate() {
        state = state.copy(showDialogAcquisition = !state.showDialogAcquisition)
    }

    fun onShowDialogCancellationDate() {
        state = state.copy(showDialogCancellation = !state.showDialogCancellation)
    }
    //endregion

    fun onClickEditProduct(goBack: () -> Unit) {
        if (areFieldEmpty()) {
            state = state.copy(isEmpty = true)
            return
        }
        if (hasValidationErrors()){
            state = state.copy(isError = true)
            return
        }
        stateView = ProductEditionStateView.Loading
        viewModelScope.launch {
            val responde = ProductRepository.existProduct(state.code)
            if (responde) {
                val product = ProductRepository.editProduct(
                    id = state.id,
                    Product(
                        id = state.id,
                        code = state.code,
                        name = state.name,
                        shortName = state.shortName,
                        description = state.description,
                        numSerial = state.numSerial,
                        codModel = state.codModel,
                        typeProduct = state.typeProduct,
                        category = state.category,
                        section = state.section,
                        status = state.status,
                        amount = state.amount,
                        price = state.price,
                        image = state.image,
                        acquisitionDate = state.acquisitionDate,
                        cancellationDate = state.cancellationDate,
                        tags = state.tags,
                        notes = state.notes
                    )
                )
                if (product) {
                    state = state.copy(success = true)
                    goBack()
                }
            }
            else{
                stateView = ProductEditionStateView.Success
                state = state.copy(isLoading = false, isExitsError = true)
            }
        }
    }

    fun onDismissDialog(){
        state = state.copy(
            isEmpty = false,
            isError = false,
            isExitsError = false
        )
    }

    private fun areFieldEmpty(): Boolean {
        return state.code.isEmpty() || state.name.isEmpty() || state.shortName.isEmpty() || state.description.isEmpty()
    }

    private fun hasValidationErrors(): Boolean {
        return state.codeError || state.nameError || state.shortNameError || state.descriptionError || state.numSerialError || state.sectionError || state.categoryError || state.codModelError || state.typeProductError || state.tagsError || state.imageError || state.notesError || state.statusError || state.priceError || state.amountError || state.cancellationDateError || state.acquisitionDateError
    }
}