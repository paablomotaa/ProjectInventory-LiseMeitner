package app.features.productcreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.Status
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.SectionRepository
import app.domain.invoicing.product.Product
import app.domain.invoicing.repositoryDB.ProductRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


private const val especialExpresion = "*/%!?()[]{}=+-_\":;,.;:|&%#@~*^`\'"

@HiltViewModel
class ProductEditionViewModel @Inject constructor(private val provideProductRepository: ProductRepositoryDB) : ViewModel() {
    var state by mutableStateOf(ProductEditionState())
        private set

    var stateView by mutableStateOf<ProductEditionStateView>(ProductEditionStateView.Loading)

    var idProduct: Long = 0

    /**
     * Import product
     * Importa el producto a editar
     *
     * @param id "id" del producto a importar
     */
    fun importProduct(id: Long){
        viewModelScope.launch {
            stateView = ProductEditionStateView.Loading
            val product = provideProductRepository.getById(id)
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

    /**
     * Get list
     * Obtiene las listas de categorias, secciones y estados para la edición
     *
     */
    fun getList(){
        viewModelScope.launch {
            val status = Status.entries
                if(status.isNotEmpty())
                    state = state.copy(listStatus = status.toList())
            CategoryRepository.getAllCategories().collect{ categories ->
                if(categories.isNotEmpty())
                    state = state.copy(listCategoria = categories)
            }
            SectionRepository.getSections().collect{ sections ->
                if(sections.isNotEmpty())
                    state = state.copy(listSeccion = sections)
            }
            stateView = ProductEditionStateView.Success
        }
    }

    //region onChange
    /**
     * On code change
     * Cambia el codigo del producto
     *
     * @param code
     */
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

    /**
     * On cod model change
     * Cambia el codigo de modelo del producto
     *
     * @param codModel
     */
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

    /**
     * On name change
     * cambia el nombre del producto
     *
     * @param name
     */
    fun onNameChange(name: String) {
        if(name.isEmpty())
            state = state.copy(name = name, nameError = true, nameFormatError = "Error de formato name")
        else
            state = state.copy(name = name, nameError = false, nameFormatError = null)
    }

    /**
     * On short name change
     * cambia el nombre corto del producto
     *
     * @param shortName
     */
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

    /**
     * On description change
     * cambia la descripcion del producto
     *
     * @param description
     */
    fun onDescriptionChange(description: String) {
        if(description.isEmpty())
            state = state.copy(description = description, descriptionError = true, descriptionFormatError = "Error de formato description")
        else
            state = state.copy(description = description, descriptionError = false, descriptionFormatError = null)
    }

    /**
     * On num serial change
     * cambia el numero de serie del producto
     *
     * @param numSerial
     */
    fun onNumSerialChange(numSerial: Double?) {
        if (numSerial == null) return
        state = state.copy(numSerial = numSerial)
    }

    /**
     * On type product change
     * cambia el tipo de producto
     *
     * @param typeProduct
     */
    fun onTypeProductChange(typeProduct: String) {
        state = state.copy(typeProduct = typeProduct)
    }

    /**
     * On category change
     * cambia la categoria del producto
     *
     * @param category
     */
    fun onCategoryChange(category: String) {
        state = state.copy(category = category)
    }

    /**
     * On section change
     * cambia la seccion del producto
     *
     * @param section
     */
    fun onSectionChange(section: String) {
        state = state.copy(section = section)
    }

    /**
     * On status change
     * cambia el estado del producto
     *
     * @param status
     */
    fun onStatusChange(status: Status) {
        state = state.copy(status = status)
    }

    /**
     * On amount change
     * cambia la cantidad del producto
     *
     * @param amount
     */
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

    /**
     * On price change
     * cambia el precio del producto
     *
     * @param price
     */
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

    /**
     * On image change
     * cambia la imagen del producto
     *
     * @param image
     */
    fun onImageChange(image: String) {
        state = state.copy(image = image)
    }

    /**
     * On acquisition date change
     * cambia la fecha de adquisición del producto
     *
     * @param acquisitionDate
     */
    fun onAcquisitionDateChange(acquisitionDate: LocalDate) {
        state = state.copy(acquisitionDate = acquisitionDate)
    }

    /**
     * On cancellation date change
     * cambia la fecha de cancelación del producto
     *
     * @param cancellationDate
     */
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

    /**
     * On notes change
     * cambia las notas del producto
     *
     * @param notes
     */
    fun onNotesChange(notes: String) {
        state = state.copy(notes = notes)
    }

    /**
     * On tags change
     * cambia los tags del producto
     *
     * @param tags
     */
    fun onTagsChange(tags: String) {
        state = state.copy(tags = tags)
    }
    //endregion

    //region onExpanded
    /**
     * On expanded tipo state
     * cambia el estado del tipo de producto
     *
     * @param expanded
     */
    fun onExpandedTipoState(expanded: Boolean) {
        state = state.copy(expandedTipoState = expanded)
    }

    /**
     * On expanded categoria state
     * cambia el estado de la categoria del producto
     *
     * @param expanded
     */
    fun onExpandedCategoriaState(expanded: Boolean) {
        state = state.copy(expandedCategoriaState = expanded)
    }

    /**
     * On expanded seccion state
     * cambia el estado de la seccion del producto
     *
     * @param expanded
     */
    fun onExpandedSeccionState(expanded: Boolean) {
        state = state.copy(expandedSeccionState = expanded)
    }

    /**
     * On expanded estado state
     * cambia el estado del estado del producto
     *
     * @param expanded
     */
    fun onExpandedEstadoState(expanded: Boolean) {
        state = state.copy(expandedEstadoState = expanded)
    }
    //endregion

    //region onShow
    /**
     * On show dialog acquisition date
     * cambia el estado del dialogo de fecha de adquisición
     *
     */
    fun onShowDialogAcquisitionDate() {
        state = state.copy(showDialogAcquisition = !state.showDialogAcquisition)
    }

    /**
     * On show dialog cancellation date
     * cambia el estado del dialogo de fecha de cancelación
     *
     */
    fun onShowDialogCancellationDate() {
        state = state.copy(showDialogCancellation = !state.showDialogCancellation)
    }
    //endregion

    /**
     * On click edit product
     * Edita el producto de la pantalla
     *
     * @param goBack
     */
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
            val responde = provideProductRepository.validate(state.code)
            if (responde) {
                val product = provideProductRepository.update(
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
                state = state.copy(success = true)
                goBack()

            }
            else{
                stateView = ProductEditionStateView.Success
                state = state.copy(isLoading = false, isExitsError = true)
            }
        }
    }

    /**
     * On dismiss dialog
     * Cambia el estado del dialogo
     *
     */
    fun onDismissDialog(){
        state = state.copy(
            isEmpty = false,
            isError = false,
            isExitsError = false
        )
    }

    /**
     * Are field empty
     * Comprueba si los campos estan vacios
     *
     * @return
     */
    private fun areFieldEmpty(): Boolean {
        return state.code.isEmpty() || state.name.isEmpty() || state.shortName.isEmpty() || state.description.isEmpty()
    }

    /**
     * Has validation errors
     * Comprueba si hay errores de validacion
     *
     * @return
     */
    private fun hasValidationErrors(): Boolean {
        return state.codeError || state.nameError || state.shortNameError || state.descriptionError || state.numSerialError || state.sectionError || state.categoryError || state.codModelError || state.typeProductError || state.tagsError || state.imageError || state.notesError || state.statusError || state.priceError || state.amountError || state.cancellationDateError || state.acquisitionDateError
    }
}