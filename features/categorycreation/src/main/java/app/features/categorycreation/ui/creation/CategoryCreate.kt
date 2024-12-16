@file:OptIn(ExperimentalMaterial3Api::class)

package app.features.categorycreation.ui.creation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import app.base.ui.composables.TopAppBarTitle
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.features.categorycreation.R
import java.util.*



@Composable
fun CategoryFormScreen(
    viewModel: CategoryCreateViewModel,
    goBack: () -> Unit,
    navController: NavController
) {
    val state = viewModel.state

    val events = CategoryCreateEvents(
        onNameChange = { viewModel.onNameChange(it) },
        onShortNameChange = { viewModel.onShortNameChange(it) },
        onDescriptionChange = { viewModel.onDescriptionChange(it) },
        onTypeChange = { viewModel.onTypeChange(it) },
        onFungibleChange = { viewModel.onFungibleChange(it) },
        onCreationClick = { viewModel.onCreationClick(navController) }
    )

    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TopAppBar(
            title = { Text(stringResource(id = R.string.title_category_create)) },
            navigationIcon = {
                IconButton(onClick = { goBack()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        )

        CategoryForm(
            goBack = goBack,
            modifier = Modifier.fillMaxWidth(),
            state = state,
            events = events,
            navController = navController
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (state.isNameError || state.isShortNameError || state.isDescriptionError || state.isImageUrlError) {
                    showErrorDialog = true
                } else {
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
                    events.onCreationClick(navController)
                    goBack()
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
    goBack: () -> Unit,
    modifier:Modifier = Modifier,
    state: CategoryCreateState,
    events: CategoryCreateEvents,
    navController: NavController
) {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = state.name,
            onValueChange = events.onNameChange,
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

        OutlinedTextField(
            value = state.shortName,
            onValueChange = events.onShortNameChange,
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

        OutlinedTextField(
            value = state.description,
            onValueChange = events.onDescriptionChange,
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
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { isDropdownExpanded = !isDropdownExpanded },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(state.type.name)
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                CategoryType.entries.forEach { categoryType ->
                    DropdownMenuItem(
                        onClick = {
                            events.onTypeChange(categoryType)
                            isDropdownExpanded = false
                        },
                        text = { Text(categoryType.name) }
                    )
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = state.isFungible,
                onCheckedChange = events.onFungibleChange
            )
            Text(stringResource(id = R.string.category_is_fungible_label))
        }
    }
}


@Preview
@Composable
fun PreviewCategoryFormScreen() {
    val viewModel = remember { CategoryCreateViewModel() }
    val navController = rememberNavController()

    CategoryFormScreen(
        viewModel = viewModel,
        goBack = {},
        navController = navController
    )
}



