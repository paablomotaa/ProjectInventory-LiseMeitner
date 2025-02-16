package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.TopAppBarTitle
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import app.base.ui.composables.BaseDropdownMenuAnyTypes
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.NormalButton
import app.base.ui.composables.topappbar.NavigationTopAppBar
import app.features.inventorycreation.R

data class RegisterEvents(
    val onCodeChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onShortNameChange: (String) -> Unit,
    //val onTypeChange:(String)-> Unit
    val onCreationClick:(() -> Unit) -> Unit,
    val onExpandedChange:(Boolean) -> Unit,
    val onValueChange:(String) -> Unit,
)

/**
 *
 * Inventory Creation
 *
 * Interfaz de inventory creation
 *
 *
 */

@Composable
fun inventoryCreationScreen(modifier:Modifier = Modifier,viewmodel:InventoryCreationViewModel,goBack:() -> Unit){
val eventos = RegisterEvents(
    onCodeChange =viewmodel::onCodeChange,
    onNameChange = viewmodel::onNameChange,
    onDescriptionChange = viewmodel::onDescriptionChange,
    onShortNameChange = viewmodel::onShortNameChange,
    onCreationClick = viewmodel::onCreationClick,
    onExpandedChange = viewmodel::onExpandeChange,
    onValueChange = viewmodel::onValueChange
)
    inventoryCreationContent(goBack,modifier,viewmodel.state,eventos)
}

@Composable
fun inventoryCreationContent(onBack:() -> Unit,modifier:Modifier = Modifier,state:InventoryCreationState,events: RegisterEvents) {

    val items = listOf("Semestral", "Anual", "Bianual")

    TopAppBarTitle<Nothing>(
        navigation = NavigationTopAppBar.BackPage( onBack, stringResource(R.string.Registrar))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BaseTextField(
                stringResource(R.string.Codigo), state.code, modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onCodeChange,
                isError = state.isCodeError,
                ErrorText = state.ErrorCodeFormat
            )
            BaseTextField(
                stringResource(R.string.Nombre),
                state.name,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onNameChange,
                isError = state.isNameError,
                ErrorText = state.ErrorNameFormat
            )
            BaseTextField(
                stringResource(R.string.Descripcion),
                state.description,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onDescriptionChange,
                isError = state.isDescriptionError,
                ErrorText = state.ErrorDescriptionFormat
            )
            BaseTextField(
                stringResource(R.string.NombreCorto),
                state.shortName,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onShortNameChange,
                isError = state.isShortNameError,
                ErrorText = state.ErrorShortNameFormat
            )
                BaseDropdownMenuAnyTypes(
                    expandeValue = state.expanded,
                    onExpandeValueChange = events.onExpandedChange,
                    text = state.type,
                    onValueChange = events.onValueChange,
                    title = "Tipo",
                    modifier = modifier,
                    option = items,
                )
            NormalButton(text = stringResource(app.base.ui.R.string.ok_button), onClick = { events.onCreationClick(onBack) })

        }
    }
}