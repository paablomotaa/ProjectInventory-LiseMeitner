package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.TopAppBarTitle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.Separations
import app.base.ui.composables.BaseDropdownMenu
import app.base.ui.composables.BaseRow
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.NormalButton
import app.base.ui.composables.SmallSpace
import app.features.inventorycreation.R

@Composable
fun inventoryCreation(modifier:Modifier = Modifier) {
    var code = rememberSaveable() { mutableStateOf("") }
    var name = rememberSaveable() { mutableStateOf("") }
    var expandedtypestate = rememberSaveable() { mutableStateOf(false) }

    val items = listOf("Semestral", "Anual", "Bianual")
    var selectedOption = rememberSaveable() { mutableStateOf<String?>(null) }

    TopAppBarTitle(title = stringResource(R.string.Registrar)) {
        Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BaseTextField(stringResource(R.string.Codigo), code, modifier = Modifier.fillMaxWidth())
            BaseTextField(stringResource(R.string.Nombre), name, modifier = Modifier.fillMaxWidth())
            BaseTextField("Descripcion", name, modifier = Modifier.fillMaxWidth())
            BaseDropdownMenu(expandedtypestate, selectedOption, stringResource(R.string.Tipo), modifier, items)

            NormalButton(text = stringResource(app.base.ui.R.string.ok_button), onClick = {})
        }
    }
}

@Preview
@Composable
fun inventoryCreationPreview(){
    inventoryCreation()
}