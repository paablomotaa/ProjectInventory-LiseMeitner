package app.base.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import app.base.utils.validateEmail

@Composable
fun EmailTextField(isEmailError: MutableState<Boolean>, email: MutableState<String>) {
    TextField(
        isError = isEmailError.value,
        supportingText = {
            Row {
                Text(
                    text = if (isEmailError.value) "Formato incorrecto:" else "",
                    Modifier.clearAndSetSemantics {})
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${email.value.length}/30")
            }
        },
        singleLine = true,
        value = email.value,
        onValueChange = {
            email.value = it
            isEmailError.value = !validateEmail(email.value)
        },
        label = { Text("Email") }
    )
}