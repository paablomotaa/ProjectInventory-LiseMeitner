package app.base.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDropdownMenu(
    expandeValue: Boolean,
    onExpandeValueChange: (Boolean) -> Unit,
    text: String,
    onValueChange: (String) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    option: List<String>
) {
    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expandeValue,
        onExpandedChange = {onExpandeValueChange(true)}) {

        TextField(
            modifier = modifier.menuAnchor(),
            label = { Text(text = (title)) },
            singleLine = true,
            value = text,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandeValue) },
            readOnly = true,
        )

        ExposedDropdownMenu(modifier = modifier.exposedDropdownSize(),
            expanded = expandeValue,
            onDismissRequest = { onExpandeValueChange(false) }) {
            option.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onValueChange(option)
                        onExpandeValueChange(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> BaseDropdownMenuAnyTypes(
    expandeValue: Boolean,
    onExpandeValueChange: (Boolean) -> Unit,
    text: T,
    onValueChange: (T) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    option: List<T>
) {
    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxSize(),
        expanded = expandeValue,
        onExpandedChange = {onExpandeValueChange(true)}) {

        TextField(
            modifier = modifier.menuAnchor(),
            label = { Text(text = (title)) },
            singleLine = true,
            value = text.toString(),
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandeValue) },
            readOnly = true,
        )

        ExposedDropdownMenu(modifier = modifier.exposedDropdownSize(),
            expanded = expandeValue,
            onDismissRequest = { onExpandeValueChange(false) }) {
            option.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.toString()) },
                    onClick = {
                        onValueChange(option)
                        onExpandeValueChange(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
fun IconDropDownMenu(
    expandeValue: Boolean,
    onExpandeValueChange: () -> Unit,
    menuItemData: List<String>,
    function: (String) -> Unit){
    DropdownMenu(
        expanded = expandeValue,
        onDismissRequest = onExpandeValueChange
    ) {
        menuItemData.forEach { option ->
            DropdownMenuItem(
                text = { Text(option) },
                onClick = { function(option) }
            )
        }
    }
}

@Composable
fun <T> IconDropDownMenuAnyType(
    expandeValue: Boolean,
    onExpandeValueChange: (Boolean) -> Unit,
    menuItemData: List<T>,
    function: (T) -> Unit){
    DropdownMenu(
        expanded = expandeValue,
        onDismissRequest = {onExpandeValueChange(false)}
    ) {
        menuItemData.forEach { option ->
            DropdownMenuItem(
                text = { Text(option.toString()) },
                onClick = { function(option) }
            )
        }
    }
}