package app.features.categorycreation.ui.edition

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.features.categorycreation.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun UpdateCategoryScreen(category: Category, onUpdate: (Category) -> Unit) {
    var name by remember { mutableStateOf(category.name) }
    var shortName by remember { mutableStateOf(category.shortName) }
    var description by remember { mutableStateOf(category.description) }
    var type by remember { mutableStateOf(category.type) }
    var isFungible by remember { mutableStateOf(category.isFungible) }
    var showTypeMenu by remember { mutableStateOf(false) }

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = dateFormat.format(category.createdDate)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(id = R.string.category_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = shortName,
            onValueChange = { shortName = it },
            label = { Text(stringResource(id = R.string.category_short_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(id = R.string.category_description_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { showTypeMenu = true },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(type.name)
            }
            DropdownMenu(
                expanded = showTypeMenu,
                onDismissRequest = { showTypeMenu = false }
            ) {
                CategoryType.values().forEach { categoryType ->
                    DropdownMenuItem(
                        onClick = {
                            type = categoryType
                            showTypeMenu = false
                        },
                        text = { Text(categoryType.name) }
                    )
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isFungible, onCheckedChange = { isFungible = it })
            Text(stringResource(id = R.string.category_is_fungible_label))
        }

        Text(text = stringResource(id = R.string.category_creation_date_label, formattedDate))

        Button(
            onClick = {
                if (name.isNotBlank() && shortName.isNotBlank()) {
                    val updatedCategory = category.copy(
                        name = name,
                        shortName = shortName,
                        description = description,
                        type = type,
                        isFungible = isFungible
                    )
                    onUpdate(updatedCategory)
                } else {
                    // TODO mostrar mensaje de error

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.update_button))
        }
    }
}
@Preview
@Composable
fun PreviewUpdateCategoryScreen() {
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

    UpdateCategoryScreen(category = sampleCategory, onUpdate = {  }) //TODO accion de actualizar
}

