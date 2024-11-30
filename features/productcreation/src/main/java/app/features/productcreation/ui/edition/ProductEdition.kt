package app.features.productcreation.ui.edition

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
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
import app.base.ui.composables.BaseTextFieldRead
import app.base.ui.composables.DateField
import app.base.ui.composables.DialogDate
import app.base.ui.composables.NormalButton
import app.base.ui.composables.TopAppBarTitle
import app.features.productcreation.R

@Composable
fun ProductEdition(modifier: Modifier = Modifier) {

    //var imagen = rememberSaveableBitmap()
    var codigo = rememberSaveable { mutableStateOf("") }
    var nombre = rememberSaveable { mutableStateOf("") }
    var descripcion = rememberSaveable { mutableStateOf("") }
    var numSerie = rememberSaveable { mutableStateOf("") }
    var codModelo = rememberSaveable { mutableStateOf("") }
    var numCantidad = rememberSaveable { mutableStateOf("") }
    var precio = rememberSaveable { mutableStateOf("") }

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

    val tipoProducto = listOf("Telefono", "Computadora", "Tablet", "Impresora")
    val categoriaList = listOf("Basico", "Economico", "Ecologico", "Premium")
    val seccionList = listOf("Seccion 1", "Seccion 2", "Seccion 3", "Seccion 4")
    val estadoList = listOf("New", "Modified", "Verified")

    TopAppBarTitle(title = stringResource(R.string.editProduct)) {
        BaseStructureColumnPaddingUpSide(modifier, Separations.Medium, scrolleable = true) {
            Card {
                BaseStructureCompletePadding(modifier, Separations.Medium) {
                    BaseImageBig()
                    BaseTextFieldRead(stringResource(R.string.code), codigo)
                    BaseTextField(stringResource(R.string.name), nombre)
                    BaseTextField(stringResource(R.string.description), descripcion)
                    BaseTextField(stringResource(R.string.numSerial), numSerie)
                    BaseTextField(stringResource(R.string.codModel), codModelo)
                    BaseRow(Separations.Small) {
                        BaseTextField(
                            stringResource(R.string.amount),
                            numCantidad,
                            Modifier.weight(1f)
                        )
                        BaseTextField(stringResource(R.string.price), precio, Modifier.weight(1f))
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
                            estadoList
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

                    NormalButton(stringResource(R.string.save), onClick = {/*TODO*/ })
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ProductEdition()
}