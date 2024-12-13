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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.Separations
import app.base.ui.composables.BaseDropdownMenu
import app.base.ui.composables.BaseRow
import app.base.ui.composables.BaseText
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.NormalButton
import app.base.ui.composables.SmallSpace
import app.features.inventorycreation.R

data class RegisterEvents(
    val onCodeChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onShortNameChange: (String) -> Unit,
    //val onTypeChange:(String)-> Unit
    val onCreationClick:() -> Unit
)

@Composable
fun inventoryCreationScreen(modifier:Modifier = Modifier,viewmodel:InventoryCreationViewModel){
val eventos = RegisterEvents(
    onCodeChange =viewmodel::onCodeChange,
    onNameChange = viewmodel::onNameChange,
    onDescriptionChange = viewmodel::onDescriptionChange,
    onShortNameChange = viewmodel::onShortNameChange,
    onCreationClick = viewmodel::onCreationClick
)
}

@Composable
fun inventoryCreationContent(modifier:Modifier = Modifier,state:InventoryCreationState,events: RegisterEvents) {

    val items = listOf("Semestral", "Anual", "Bianual")

    TopAppBarTitle(
        title = stringResource(R.string.Registrar),
        onBack = {}
    ) {
        Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BaseTextField(
                stringResource(R.string.Codigo), state.code, modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onCodeChange
            )
            BaseTextField(
                stringResource(R.string.Nombre),
                state.name,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onNameChange
            )
            BaseTextField(
                stringResource(R.string.Descripcion),
                state.description,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onDescriptionChange,
            )
            BaseTextField(
                stringResource(R.string.NombreCorto),
                state.shortName,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onShortNameChange)

            NormalButton(text = stringResource(app.base.ui.R.string.ok_button), onClick = events.onCreationClick)

        }
    }
}

@Preview
@Composable
fun inventoryCreationPreview(){
    val viewmodel = remember { InventoryCreationViewModel() }
    inventoryCreationScreen(
        viewmodel = viewmodel
    )
}