package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.TopAppBarTitle
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.base.ui.composables.BaseDropdownMenu
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.NormalButton
import app.features.inventorycreation.R

data class RegisterEvents(
    val onCodeChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onShortNameChange: (String) -> Unit,
    //val onTypeChange:(String)-> Unit
    val onCreationClick:(NavController) -> Unit
)

@Composable
fun inventoryCreationScreen(modifier:Modifier = Modifier,viewmodel:InventoryCreationViewModel,goBack:() -> Unit,navController: NavController){
val eventos = RegisterEvents(
    onCodeChange =viewmodel::onCodeChange,
    onNameChange = viewmodel::onNameChange,
    onDescriptionChange = viewmodel::onDescriptionChange,
    onShortNameChange = viewmodel::onShortNameChange,
    onCreationClick = viewmodel::onCreationClick
)
    inventoryCreationContent(goBack,modifier,viewmodel.state,eventos,navController)
}

@Composable
fun inventoryCreationContent(onBack:() -> Unit,modifier:Modifier = Modifier,state:InventoryCreationState,events: RegisterEvents,navController: NavController) {

    val items = listOf("Semestral", "Anual", "Bianual")

    TopAppBarTitle(
        title = stringResource(R.string.Registrar),
        onBack = onBack
    ) {
        Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
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

            NormalButton(text = stringResource(app.base.ui.R.string.ok_button), onClick = { events.onCreationClick(navController) })

        }
    }
}

@Preview
@Composable
fun inventoryCreationPreview(){
    val viewmodel = remember {InventoryCreationViewModel()}
    val navController = rememberNavController()
    inventoryCreationScreen(
        viewmodel = viewmodel,
        goBack = {},
        navController = navController
    )
}