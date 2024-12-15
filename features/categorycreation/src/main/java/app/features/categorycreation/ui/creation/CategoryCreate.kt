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
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.features.categorycreation.R
import java.util.*


@Composable
fun CategoryFormScreen(viewModel: CategoryCreateViewModel, onSave: (Category) -> Unit) {
    val context = LocalContext.current
    val state = viewModel.state

    CategoryForm(
        state = state,
        onNameChange = { name -> viewModel.onNameChange(name, context) },
        onShortNameChange = { shortName -> viewModel.onShortNameChange(shortName, context) },
        onDescriptionChange = { description -> viewModel.onDescriptionChange(description, context) },
        onTypeChange = { type -> viewModel.onTypeChange(type) },
        onFungibleChange = { fungible -> viewModel.onFungibleChange(fungible) }
    )

    Button(
        onClick = {
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
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = state.isValidForm()
    ) {
        Text(stringResource(id = R.string.save_button))
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
        TextField(
            value = state.name,
            onValueChange = onNameChange,
            label = { Text(stringResource(id = R.string.category_name_label)) },
            isError = state.isNameError,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.shortName,
            onValueChange = onShortNameChange,
            label = { Text(stringResource(id = R.string.category_short_name_label)) },
            isError = state.isShortNameError,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.description,
            onValueChange = onDescriptionChange,
            label = { Text(stringResource(id = R.string.category_description_label)) },
            isError = state.isDescriptionError,
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { isDropdownExpanded = !isDropdownExpanded },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(state.type.name)
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                CategoryType.values().forEach { categoryType ->
                    DropdownMenuItem(
                        onClick = {
                            onTypeChange(categoryType)
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
                onCheckedChange = onFungibleChange
            )
            Text(stringResource(id = R.string.category_is_fungible_label))
        }
    }
}


@Preview
@Composable
fun PreviewCategoryFormScreen() {
    val sampleCategory = Category(
        id = "1",
        name = "Electrónica",
        shortName = "ELE",
        description = "Electrodomésticos y gadgets",
        imageUrl = "https://image.url",
        createdDate = Date(),
        type = CategoryType.PREMIUM,
        isFungible = true
    )
    val viewModel = CategoryCreateViewModel()
    CategoryFormScreen(viewModel = viewModel, onSave = {})
}



