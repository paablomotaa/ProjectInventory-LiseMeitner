package app.features.inventorycreation.ui.edition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseDropdownMenuAnyTypes
import app.base.ui.composables.BaseTextField
import app.base.ui.composables.NormalButton
import app.base.ui.composables.TopAppBarTitle
import app.base.ui.composables.topappbar.NavigationTopAppBar
import app.features.inventorycreation.R
import kotlin.reflect.KFunction1


data class EditEvents(
    val onCodeChange: KFunction1<String, Unit>,
    val onNameChange: KFunction1<String, Unit>,
    val onDescriptionChange: KFunction1<String, Unit>,
    val onShortNameChange: KFunction1<String, Unit>,
    val onEditClick: KFunction1<() -> Unit, Unit>,
    val onExpandedChange: KFunction1<Boolean, Unit>,
    val onValueChange: KFunction1<String, Unit>
)
@Composable
fun inventoryEdition(modifier: Modifier = Modifier, onBack: () -> Unit, onAccept: () -> Unit, viewModel: InventoryEditionViewModel, events: EditEvents) {
    val items = listOf("Semestral", "Anual", "Bianual")

    TopAppBarTitle(navigation = NavigationTopAppBar.BackPage(onBack, stringResource(R.string.Registrar))) {
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
                text = stringResource(R.string.Codigo),
                viewModel.state.code, onValueChange = events.onCodeChange,
                modifier = Modifier.fillMaxWidth(),
                isError = viewModel.state.isCodeError,
                ErrorText = viewModel.state.ErrorCodeFormat
            )
            BaseTextField(
                stringResource(R.string.Nombre),
                viewModel.state.name,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = events.onNameChange,
                isError = viewModel.state.isNameError,
                ErrorText = viewModel.state.ErrorNameFormat
            )
            BaseTextField(
                stringResource(R.string.Descripcion),
                viewModel.state.description, onValueChange = events.onDescriptionChange,
                isError = viewModel.state.isDescriptionError,
                modifier = Modifier.fillMaxWidth(),
                ErrorText = viewModel.state.ErrorDescriptionFormat
            )
            BaseTextField(
                stringResource(R.string.NombreCorto),
                viewModel.state.shortName, onValueChange = events.onShortNameChange,
                isError = viewModel.state.isShortNameError,
                modifier = Modifier.fillMaxWidth(),
                ErrorText = viewModel.state.ErrorShortNameFormat
            )

            BaseDropdownMenuAnyTypes(
                expandeValue = viewModel.state.expanded,
                onExpandeValueChange = events.onExpandedChange,
                text = viewModel.state.type,
                onValueChange = events.onValueChange,
                title = "Tipo",
                modifier = modifier,
                option = items
            )

            NormalButton(
                text = stringResource(app.base.ui.R.string.ok_button),
                onClick = { events.onEditClick { onAccept() } }
            )
        }
    }
}

@Composable
fun inventoryEditionScreen(modifier: Modifier = Modifier, viewModel: InventoryEditionViewModel, goBack: () -> Unit, onAccept: () -> Unit, navController: NavController) {
    val eventos = EditEvents(
        onCodeChange = viewModel::onCodeChange,
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onShortNameChange = viewModel::onShortNameChange,
        onEditClick = viewModel::onEditClick,
        onExpandedChange = viewModel::onExpandeChange,
        onValueChange = viewModel::onValueChange
    )

    val inventoryId = navController.currentBackStackEntry?.arguments?.getInt("inventoryId")
    LaunchedEffect(inventoryId) {
        if (inventoryId != null && viewModel.state.id == 0) {
            viewModel.getInventory(inventoryId)
        }
    }

    when {
        viewModel.stateView is InventoryEditionStateView.Loading -> { LoadingUi() }
        viewModel.stateView is InventoryEditionStateView.Success -> {
            inventoryEdition(modifier, goBack, onAccept, viewModel, eventos)
        }
    }
}