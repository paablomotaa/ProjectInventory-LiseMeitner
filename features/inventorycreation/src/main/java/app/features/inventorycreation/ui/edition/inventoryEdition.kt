package app.features.inventorycreation.ui.edition

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
import app.features.inventorycreation.R

@Composable
fun inventoryEdition(modifier: Modifier = Modifier){
    var code = rememberSaveable() { mutableStateOf("") }
    var name = rememberSaveable() { mutableStateOf("") }
    var expandedtypestate = rememberSaveable() { mutableStateOf(false) }

    val items = listOf("Semestral", "Anual", "Bianual")
    var selectedOption = rememberSaveable() { mutableStateOf<String?>(null) }
    TopAppBarTitle(title = stringResource(R.string.Editar)) {
        Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BaseTextFieldRead(stringResource(R.string.Codigo), code, modifier = Modifier.fillMaxWidth())
            BaseTextField(stringResource(R.string.Nombre), name, modifier = Modifier.fillMaxWidth())
            BaseTextField(stringResource(R.string.Descripcion), name, modifier = Modifier.fillMaxWidth())
            BaseDropdownMenu(expandedtypestate, selectedOption, stringResource(R.string.Tipo), modifier, items)

            NormalButton(text = stringResource(app.base.ui.R.string.ok_button), onClick = {})
        }
    }
}
@Preview
@Composable
fun invetoryEditionPreview(){
    inventoryEdition()
}