package app.features.productcreation.ui.creation

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Separations
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.BaseDropdownMenu
import app.base.ui.composables.BaseDropdownMenuAnyTypes
import app.base.ui.composables.BaseImageBig
import app.base.ui.composables.BaseRow
import app.base.ui.composables.BaseStructureColumnPaddingUpSide
import app.base.ui.composables.BaseStructureCompletePadding
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.BaseTextFieldDouble
import app.base.ui.composables.BaseTextFieldInt
import app.base.ui.composables.BaseTextFieldNoError
import app.base.ui.composables.DateField
import app.base.ui.composables.DialogDate
import app.base.ui.composables.NormalButton
import app.base.ui.composables.TopAppBarTitle
import app.base.utils.Status
import app.domain.invoicing.category.Category
import app.features.productcreation.R
import java.time.LocalDate

@Composable
fun ProductCreationScreen(goBack: () -> Unit,viewModel: ProductCreationViewModel,
                          modifier: Modifier = Modifier,
                          event: ProductCreationEvent = ProductCreationEvent(
                              onCodeChange = viewModel::onCodeChange,
                              onNameChange = viewModel::onNameChange,
                              onShortNameChange = viewModel::onShortNameChange,
                              onDescriptionChange = viewModel::onDescriptionChange,
                              onNumSerialChange = viewModel::onNumSerialChange,
                              onCodModelChange = viewModel::onCodModelChange,
                              onAmountChange = viewModel::onAmountChange,
                              onPriceChange = viewModel::onPriceChange,
                              onTypeProductChange = viewModel::onTypeProductChange,
                              onCategoryChange = viewModel::onCategoryChange,
                              onSectionChange = viewModel::onSectionChange,
                              onStatusChange = viewModel::onStatusChange,
                              onImageChange = viewModel::onImageChange,
                              onAcquisitionDateChange = viewModel::onAcquisitionDateChange,
                              onCancellationDateChange = viewModel::onCancellationDateChange,
                              onNotesChange = viewModel::onNotesChange,
                              onTagsChange = viewModel::onTagsChange,
                              onExpandedTipoState = viewModel::onExpandedTipoState,
                              onExpandedCategoriaState = viewModel::onExpandedCategoriaState,
                              onExpandedSeccionState = viewModel::onExpandedSeccionState,
                              onExpandedEstadoState = viewModel::onExpandedEstadoState,
                              onShowDialogAcquisitionDate = viewModel::onShowDialogAcquisitionDate,
                              onShowDialogCancellationDate = viewModel::onShowDialogCancellationDate,
                              onDismissDialog = viewModel::onDismissDialog,
                              onClickCreateProduct = viewModel::onClickCreateProduct,
                          )){
    when{
        viewModel.state.isLoading -> LoadingUi()
        viewModel.state.isExitsError -> BaseAlertDialog(
            "Error",
            "Producto ya creado",
            "Aceptar",
            onConfirm = event.onDismissDialog,
            onDismiss = event.onDismissDialog)
        viewModel.state.isEmpty -> BaseAlertDialog(
            "Error",
            "Campos vacios",
            "Aceptar",
            onConfirm = event.onDismissDialog,
            onDismiss = event.onDismissDialog)
        viewModel.state.isError -> BaseAlertDialog(
            "Error",
            "Hay error en el formulario",
            "Aceptar",
            onConfirm = event.onDismissDialog,
            onDismiss = event.onDismissDialog)
        else -> ProductCreation(modifier, goBack, viewModel.state, event)
    }

}

data class ProductCreationEvent(
    val onCodeChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onShortNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onNumSerialChange: (Double?) -> Unit = {},
    val onCodModelChange: (String) -> Unit = {},
    val onAmountChange: (Int?) -> Unit = {},
    val onPriceChange: (Double?) -> Unit = {},
    val onTypeProductChange: (String) -> Unit = {},
    val onCategoryChange: (Category?) -> Unit = {},
    val onSectionChange: (String) -> Unit = {},
    val onStatusChange: (Status) -> Unit = {},
    val onImageChange: (String) -> Unit = {},
    val onAcquisitionDateChange: (LocalDate) -> Unit = {},
    val onCancellationDateChange: (LocalDate) -> Unit = {},
    val onNotesChange: (String) -> Unit = {},
    val onTagsChange: (String) -> Unit = {},

    val onExpandedTipoState: () -> Unit = {},
    val onExpandedCategoriaState: () -> Unit = {},
    val onExpandedSeccionState: () -> Unit = {},
    val onExpandedEstadoState: () -> Unit = {},

    val onShowDialogAcquisitionDate: () -> Unit = {},
    val onShowDialogCancellationDate: () -> Unit = {},

    val onDismissDialog: () -> Unit = {},

    val onClickCreateProduct: () -> Unit = {}
    )

@Composable
fun ProductCreation(modifier: Modifier = Modifier, goBack: () -> Unit, state: ProductCreationState,event: ProductCreationEvent) {

    //var imagen = rememberSaveableBitmap()
    /*
    var code = rememberSaveable { mutableStateOf("") }
    var name = rememberSaveable { mutableStateOf("") }
    var description = rememberSaveable { mutableStateOf("") }
    var numSerial = rememberSaveable { mutableStateOf("") }
    var codModel = rememberSaveable { mutableStateOf("") }
    var amount = rememberSaveable { mutableStateOf("") }
    var price = rememberSaveable { mutableStateOf("") }

    var expandedTipoState = rememberSaveable { mutableStateOf(false) }
    var tipoSelected = rememberSaveable { mutableStateOf("") }
    var expandedCategoriaState = rememberSaveable { mutableStateOf(false) }
    var categoriaSelected = rememberSaveable { mutableStateOf("") }
    var expandedSeccionState = rememberSaveable { mutableStateOf(false) }
    var seccionSelected = rememberSaveable { mutableStateOf("") }
    var expandedEstadoState = rememberSaveable { mutableStateOf(false) }
    var estadoSelected = rememberSaveable { mutableStateOf("") }
    var selectedDateAqui = rememberSaveable { mutableStateOf("") }
    var showDialogAqui = rememberSaveable { mutableStateOf(false) }
    var isDateErrorAqui = rememberSaveable { mutableStateOf(false) }
    var selectedDateBaja = rememberSaveable { mutableStateOf("") }
    var showDialogBaja = rememberSaveable { mutableStateOf(false) }
    var isDateErrorBaja = rememberSaveable { mutableStateOf(false) }

    var notas = rememberSaveable { mutableStateOf("") }
    var tags = rememberSaveable { mutableStateOf("") }

    val statusOptions = Status.entries.map { it.name }
    val categoryOptions = rememberSaveable { mutableStateListOf<List<Category>>(emptyList()) }
    //val secctionOptions = rememberSaveable { mutableStateListOf<List<Section>>(emptyList()) }

    //region Prueba
    val tipoProducto = listOf("Telefono", "Computadora", "Tablet", "Impresora")
    val categoriaList = listOf("Basico", "Economico", "Ecologico", "Premium")
    val seccionList = listOf("Seccion 1", "Seccion 2", "Seccion 3", "Seccion 4")
    //endregion
    */

    TopAppBarTitle(title = stringResource(R.string.createProduct),goBack) {
        BaseStructureColumnPaddingUpSide(modifier, Separations.Medium, scrolleable = true) {
            Card {
                BaseStructureCompletePadding(modifier, Separations.Medium) {
                    BaseImageBig()
                    BaseTextField(stringResource(R.string.code), state.code, event.onCodeChange, isError = state.codeError, ErrorText = state.codeFormatError)
                    BaseTextField(stringResource(R.string.name), state.name, event.onNameChange, isError = state.nameError, ErrorText = state.nameFormatError)
                    BaseTextField(stringResource(R.string.description), state.description, event.onDescriptionChange, isError = state.descriptionError, ErrorText = state.descriptionFormatError)
                    BaseTextFieldDouble(stringResource(R.string.numSerial), state.numSerial.toString(), event.onNumSerialChange)
                    BaseTextField(stringResource(R.string.codModel), state.codModel, event.onCodModelChange, isError = state.codModelError, ErrorText = state.codModelFormatError)
                    BaseRow(Separations.Small) {
                        BaseTextFieldInt(stringResource(R.string.amount), state.amount.toString(), event.onAmountChange, Modifier.weight(1f))
                        BaseTextFieldDouble(stringResource(R.string.price), state.price.toString(), event.onPriceChange, Modifier.weight(1f))
                    }
                    BaseRow(Separations.Small) {
                        BaseDropdownMenu(
                            state.expandedTipoState,
                            event.onExpandedTipoState,
                            state.typeProduct,
                            event.onTypeProductChange,
                            stringResource(R.string.typeProduct),
                            Modifier.weight(5f),
                            state.listTipoProducto
                        )
                        BaseDropdownMenuAnyTypes(
                            state.expandedCategoriaState,
                            event.onExpandedCategoriaState,
                            state.category,
                            event.onCategoryChange,
                            stringResource(R.string.category),
                            Modifier.weight(4f),
                            state.listCategoria
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseDropdownMenu(
                            state.expandedSeccionState,
                            event.onExpandedSeccionState,
                            state.section,
                            event.onSectionChange,
                            stringResource(R.string.section),
                            Modifier.weight(1f),
                            state.listSeccion
                        )
                        BaseDropdownMenuAnyTypes(
                            state.expandedEstadoState,
                            event.onExpandedEstadoState,
                            state.status,
                            event.onStatusChange,
                            stringResource(R.string.status),
                            Modifier.weight(1f),
                            state.listStatus
                        )
                    }
                    BaseRow(Separations.Small) {
                        DateField(
                            event.onShowDialogAcquisitionDate,
                            state.acquisitionDate,
                            state.acquisitionDateError,
                            stringResource(R.string.acquisitionDate),
                            state.acquisitionDateFormatError,
                            Modifier.weight(1f)
                        )

                        DialogDate(state.showDialogAcquisition, event.onShowDialogAcquisitionDate, event.onAcquisitionDateChange)
                        DateField(
                            event.onShowDialogCancellationDate,
                            state.cancellationDate,
                            state.cancellationDateError,
                            stringResource(R.string.cancellationDate),
                            state.cancellationDateFormatError,
                            Modifier.weight(1f)
                        )
                        DialogDate(state.showDialogCancellation, event.onShowDialogCancellationDate, event.onCancellationDateChange)
                    }
                    BaseTextFieldNoError(stringResource(R.string.notes), state.notes, event.onNotesChange)

                    BaseTextFieldNoError(stringResource(R.string.tags), state.tags, event.onTagsChange)

                    NormalButton(stringResource(R.string.create), onClick = event.onClickCreateProduct)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val viewModel = remember{ProductCreationViewModel()}
    viewModel.getList()
    ProductCreationScreen(goBack = {}, viewModel)
}