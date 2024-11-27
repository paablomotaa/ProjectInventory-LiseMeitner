package app.base.ui.composables

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun BaseTextField(text: String, value: MutableState<String>, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(text) },
        )
}

@Composable
fun BaseTextFieldRead(text: String, value: MutableState<String>, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        singleLine = true,
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(text) },
        readOnly = true
    )
}