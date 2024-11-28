package app.features.productcreation.ui.creation

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Separations
import app.base.ui.composables.BaseDropdownMenu
import app.base.ui.composables.BaseImageBig
import app.base.ui.composables.BaseRow
import app.base.ui.composables.BaseStructureColumnPaddingUpSide
import app.base.ui.composables.BaseStructureCompletePadding
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.DateField
import app.base.ui.composables.DialogDate
import app.base.ui.composables.NormalButton
import app.base.ui.composables.TopAppBarTitle
import app.base.utils.Status
import app.domain.invoicing.category.Category
import app.features.productcreation.R


@Composable
fun ProductCreation(modifier: Modifier = Modifier) {

    //var imagen = rememberSaveableBitmap()
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


    TopAppBarTitle(title = "Crear Producto") {
        BaseStructureColumnPaddingUpSide(modifier, Separations.Medium, scrolleable = true) {
            Card {
                BaseStructureCompletePadding(modifier, Separations.Medium) {
                    BaseImageBig()
                    BaseTextField(stringResource(R.string.code), code)
                    BaseTextField(stringResource(R.string.name), name)
                    BaseTextField(stringResource(R.string.description), description)
                    BaseTextField(stringResource(R.string.numSerial), numSerial)
                    BaseTextField(stringResource(R.string.codModel), codModel)
                    BaseRow(Separations.Small) {
                        BaseTextField(stringResource(R.string.amount), amount, Modifier.weight(1f))
                        BaseTextField(stringResource(R.string.price), price, Modifier.weight(1f))
                    }
                    BaseRow(Separations.Small) {
                        BaseDropdownMenu(
                            expandedTipoState,
                            tipoSelected,
                            stringResource(R.string.typeProduct),
                            Modifier.weight(5f),
                            tipoProducto
                        )
                        BaseDropdownMenu(
                            expandedCategoriaState,
                            categoriaSelected,
                            stringResource(R.string.category),
                            Modifier.weight(4f),
                            categoriaList
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseDropdownMenu(
                            expandedSeccionState,
                            seccionSelected,
                            stringResource(R.string.section),
                            Modifier.weight(1f),
                            seccionList
                        )
                        BaseDropdownMenu(
                            expandedEstadoState,
                            estadoSelected,
                            stringResource(R.string.status),
                            Modifier.weight(1f),
                            statusOptions
                        )
                    }
                    BaseRow(Separations.Small) {
                        DateField(
                            showDialogAqui,
                            selectedDateAqui,
                            isDateErrorAqui,
                            stringResource(R.string.acquisitionDate),
                            Modifier.weight(1f)
                        )

                        DialogDate(showDialogAqui, selectedDateAqui, isDateErrorAqui)
                        DateField(
                            showDialogBaja,
                            selectedDateBaja,
                            isDateErrorBaja,
                            stringResource(R.string.cancellationDate),
                            Modifier.weight(1f)
                        )
                        DialogDate(showDialogBaja, selectedDateBaja, isDateErrorBaja)
                    }
                    BaseTextField(stringResource(R.string.notes), notas)

                    BaseTextField(stringResource(R.string.tags), tags)

                    NormalButton(stringResource(R.string.create), onClick = {/*TODO*/ })
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ProductCreation()
}