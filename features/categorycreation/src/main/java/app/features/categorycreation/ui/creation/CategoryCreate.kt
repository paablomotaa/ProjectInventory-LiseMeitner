package app.features.categorycreation.ui.creation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.features.categorycreation.R
import java.util.*


@Composable
fun CategoryFormScreen(
    viewModel: CategoryCreateViewModel,
    onSave: (Category) -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val state = viewModel.state
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CategoryForm(
            state = state,
            onNameChange = { name -> viewModel.onNameChange(name, context) },
            onShortNameChange = { shortName -> viewModel.onShortNameChange(shortName, context) },
            onDescriptionChange = { description -> viewModel.onDescriptionChange(description, context) },
            onTypeChange = { type -> viewModel.onTypeChange(type) },
            onFungibleChange = { fungible -> viewModel.onFungibleChange(fungible) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                // Verificar si hay errores de validación
                if (state.isNameError || state.isShortNameError || state.isDescriptionError || state.isImageUrlError) {
                    showErrorDialog = true
                } else {
                    // Crear la categoría si no hay errores
                    val category = Category(
                        id = UUID.randomUUID().toString(),
                        name = state.name,
                        shortName = state.shortName,
                        description = state.description,
                        imageUrl = state.imageUrl,
                        createdDate = Date(),
                        type = state.type,
                        isFungible = state.isFungible
                    )
                    onSave(category)
                    showSuccessDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.save_button))
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text(stringResource(id = R.string.success_title)) },
            text = { Text(stringResource(id = R.string.success_message)) },
            confirmButton = {
                Button(onClick = {
                    showSuccessDialog = false
                    // Navegar hacia atrás después de guardar la categoría
                    navController.popBackStack()
                }) {
                    Text(stringResource(id = R.string.ok_button))
                }
            }
        )
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text(stringResource(id = R.string.error_title)) },
            text = { Text(stringResource(id = R.string.error_category_creation)) },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }) {
                    Text(stringResource(id = R.string.ok_button))
                }
            }
        )
    }
}

@Composable
fun CategoryForm(
    state: CategoryCreateState,
    onNameChange: (String) -> Unit,
    onShortNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTypeChange: (CategoryType) -> Unit,
    onFungibleChange: (Boolean) -> Unit
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Campo Nombre
        OutlinedTextField(
            value = state.name,
            onValueChange = onNameChange,
            label = { Text(stringResource(id = R.string.category_name_label)) },
            isError = state.isNameError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (state.isNameError) {
                    Text(
                        text = stringResource(id = R.string.error_name_format),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )

        // Campo Nombre Corto
        OutlinedTextField(
            value = state.shortName,
            onValueChange = onShortNameChange,
            label = { Text(stringResource(id = R.string.category_short_name_label)) },
            isError = state.isShortNameError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (state.isShortNameError) {
                    Text(
                        text = stringResource(id = R.string.error_short_name_format),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )

        // Campo Descripción
        OutlinedTextField(
            value = state.description,
            onValueChange = onDescriptionChange,
            label = { Text(stringResource(id = R.string.category_description_label)) },
            isError = state.isDescriptionError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3,
            supportingText = {
                if (state.isDescriptionError) {
                    Text(
                        text = stringResource(id = R.string.error_description_format),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )

        // Dropdown para el tipo de categoría
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { isDropdownExpanded = !isDropdownExpanded },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(state.type.name) // Mostrar el nombre directamente
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                CategoryType.entries.forEach { categoryType ->
                    DropdownMenuItem(
                        onClick = {
                            onTypeChange(categoryType) // Pasamos el enum completo
                            isDropdownExpanded = false
                        },
                        text = { Text(categoryType.name) } // Mostrar el nombre
                    )
                }
            }
        }

        // Checkbox para la fungibilidad
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = state.isFungible,
                onCheckedChange = onFungibleChange
            )
            Text(stringResource(id = R.string.category_is_fungible_label))
        }
    }
}


@Preview
@Composable
fun PreviewCategoryFormScreen() {
    val viewModel = CategoryCreateViewModel()

    // Simula un NavController en la previsualización (sin funcionalidad real)
    val navController = rememberNavController()

    // Pasa el NavController simulado y el onSave a la pantalla
    CategoryFormScreen(
        viewModel = viewModel,
        onSave = { navController.popBackStack() },  // Simula la navegación
        navController = navController
    )
}



