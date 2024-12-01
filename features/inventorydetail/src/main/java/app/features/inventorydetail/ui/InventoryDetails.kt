package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.BaseDropdownMenu
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.BaseTextFieldRead
import app.base.ui.composables.NormalButton
import app.base.ui.composables.TopAppBarTitle
import app.features.inventorydetail.R

@Composable
fun InventoryDetails(){
    var code = rememberSaveable() { mutableStateOf("") }
    var name = rememberSaveable() { mutableStateOf("") }
    var description = rememberSaveable() { mutableStateOf("") }
    var typeSelected = rememberSaveable() { mutableStateOf("") }
    var dateActive = rememberSaveable() { mutableStateOf("") }


    TopAppBarTitle(title = stringResource(R.string.Detalles)) {
        Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BaseTextFieldRead(stringResource(R.string.Codigo),code)
            BaseTextFieldRead(stringResource(R.string.Nombre), name)
            BaseTextFieldRead(stringResource(R.string.Descripcion), description)
            BaseTextFieldRead(stringResource(R.string.Tipo),typeSelected)
            BaseTextFieldRead(stringResource(R.string.FechActivo),dateActive)
        }
    }
}

@Preview
@Composable
fun InventoryDetailsPreview(){
    InventoryDetails()
}