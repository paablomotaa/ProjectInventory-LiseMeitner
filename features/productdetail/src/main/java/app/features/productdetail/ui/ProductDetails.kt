package app.features.productdetail.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Separations
import app.base.ui.composables.BaseImageBig
import app.base.ui.composables.BaseRow
import app.base.ui.composables.BaseStructureColumnPaddingUpSide
import app.base.ui.composables.BaseStructureCompletePadding
import app.base.ui.composables.BaseTextFieldRead
import app.base.ui.composables.TopAppBarOneAction
import app.features.productdetail.R

@Composable
fun ProductDetails(modifier: Modifier = Modifier) {

    var codigo = rememberSaveable { mutableStateOf("") }
    var nombre = rememberSaveable { mutableStateOf("") }
    var descripcion = rememberSaveable { mutableStateOf("") }
    var numSerie = rememberSaveable { mutableStateOf("") }
    var codModelo = rememberSaveable { mutableStateOf("") }
    var numCantidad = rememberSaveable { mutableStateOf("") }
    var precio = rememberSaveable { mutableStateOf("") }
    var tipoSelected = rememberSaveable { mutableStateOf("") }
    var seccionSelected = rememberSaveable { mutableStateOf("") }
    var estadoSelected = rememberSaveable { mutableStateOf("") }
    var selectedDate = rememberSaveable { mutableStateOf("") }
    var notas = rememberSaveable { mutableStateOf("") }
    var tags = rememberSaveable { mutableStateOf("") }

    TopAppBarOneAction(title = nombre.value, Icons.Default.Edit, stringResource(R.string.edit), funtion = {}) {
        BaseStructureColumnPaddingUpSide(modifier, Separations.Medium, scrolleable = true) {
            Card {
                BaseStructureCompletePadding(modifier, Separations.Medium) {
                    BaseImageBig()
                    BaseTextFieldRead(stringResource(R.string.code), codigo)
                    BaseTextFieldRead(stringResource(R.string.name), nombre)
                    BaseTextFieldRead(stringResource(R.string.description), descripcion)
                    BaseTextFieldRead(stringResource(R.string.numSerial), numSerie)
                    BaseTextFieldRead(stringResource(R.string.codModel), codModelo)
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.amount),
                            numCantidad,
                            Modifier.weight(1f)
                        )
                        BaseTextFieldRead(
                            stringResource(R.string.price),
                            precio,
                            Modifier.weight(1f)
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.typeProduct),
                            tipoSelected,
                            Modifier.weight(5f)
                        )
                        BaseTextFieldRead(
                            stringResource(R.string.category),
                            tipoSelected,
                            Modifier.weight(4f)
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.section),
                            seccionSelected,
                            Modifier.weight(1f)
                        )
                        BaseTextFieldRead(
                            stringResource(R.string.status),
                            estadoSelected,
                            Modifier.weight(1f)
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.acquisitionDate),
                            selectedDate,
                            Modifier.weight(1f)
                        )
                        BaseTextFieldRead(
                            stringResource(R.string.cancellationDate),
                            selectedDate,
                            Modifier.weight(1f)
                        )
                    }
                    BaseTextFieldRead(stringResource(R.string.notes), notas)
                    BaseTextFieldRead(stringResource(R.string.tags), tags)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ProductDetails()
}