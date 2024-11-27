package app.base.ui.composables

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
fun BaseDropdownMenu(value: MutableState<Boolean>, texto: MutableState<String>, text: String, modifier: Modifier = Modifier){
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = value.value,
            onExpandedChange = {value.value = !value.value}) {

        TextField( label = { Text(text=(text)) },
            value = texto.value,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = value.value)},
            readOnly = true,
        )

        ExposedDropdownMenu(expanded = value.value,
            onDismissRequest = {value.value = false}) { }
    }
}