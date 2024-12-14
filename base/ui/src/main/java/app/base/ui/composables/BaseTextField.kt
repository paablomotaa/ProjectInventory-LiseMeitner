package app.base.ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BaseTextField(text: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text) },
    )
}

@Composable
fun BaseTextFieldRead(text: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text) },
        readOnly = true
    )
}

@Composable
fun BaseTextFieldInt(text: String, value: String, onValueChange: (Int?) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value,
        onValueChange = { newValue ->
            val intValue = newValue.toIntOrNull()
            onValueChange(intValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text) },
    )
}

@Composable
fun BaseTextFieldDouble(text: String, value: String, onValueChange: (Double?) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value,
        onValueChange = { newValue ->
            val doubleValue = newValue.toDoubleOrNull()
            onValueChange(doubleValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        label = { Text(text) },
    )
}