package app.base.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDropdownMenu(
    valueChange: MutableState<Boolean>,
    text: MutableState<String?>,
    title: String,
    modifier: Modifier = Modifier,
    option: List<String>
) {
    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxSize(),
        expanded = valueChange.value,
        onExpandedChange = { valueChange.value = !valueChange.value }) {

        TextField(
            modifier = modifier.menuAnchor(),
            label = { Text(text = (title)) },
            singleLine = true,
            value = text.value.toString(),
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = valueChange.value) },
            readOnly = true,
        )

        ExposedDropdownMenu(modifier = modifier.exposedDropdownSize(),
            expanded = valueChange.value,
            onDismissRequest = { valueChange.value = false }) {
            option.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        text.value = option
                        valueChange.value = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}