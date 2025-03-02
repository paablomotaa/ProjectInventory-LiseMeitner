package app.base.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import app.base.ui.Dimensions
import app.base.ui.Separations
import com.example.login.R
import java.util.Date

/**
 * Loading animation
 *
 */

@Composable
fun EmailField(isEmailError: Boolean, email: String, onEmailChange: (String) -> Unit, emailFormat: String?) {
    TextField(
        isError = isEmailError,
        supportingText = {
            Row {
                Text(
                    text = emailFormat ?: "",
                    Modifier.clearAndSetSemantics {})
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${email.length}/30")
            }
        },
        singleLine = true,
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") }
    )
}

@Composable
fun PasswordField(password: String, passwordVisible: Boolean, isPasswordError: Boolean, onPasswordChange: (String) -> Unit, onClickPassword: (Boolean) -> Unit, passworFormat: String?) {
    TextField(
        isError = isPasswordError,
        singleLine = true,
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { onClickPassword(passwordVisible) }){
                Icon(imageVector  = image, contentDescription = null)
            }
        },
        supportingText = {
            Row {
                Text(
                    text = passworFormat ?: "",
                    Modifier.clearAndSetSemantics {})
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}

@Composable
fun BaseField(text: String, value: String, onValueChange: (String) -> Unit, isFieldError: Boolean, fieldFormat: String?, modifier: Modifier = Modifier) {

    TextField(
        modifier = modifier,
        isError = isFieldError,
        supportingText = {
            Row {
                Text(
                    text = fieldFormat ?: "")
            }
        },
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text) }
    )
}

@Composable
fun TitleTxt(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = Dimensions.Medium,
        text = text,
    )
}

@Composable
fun DateField(
    onShowDialogChange: (Boolean) -> Unit,
    selectedDate: String,
    isDateError: Boolean,
    dateFormat: String?
) {
    TextField(
        isError = isDateError,
        singleLine = true,
        value = selectedDate,
        label = { Text("Fecha de nacimiento") },
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                    onShowDialogChange(true)
                }){
                Icon(Icons.Default.DateRange, contentDescription = null)
            }

        },
        supportingText = {
            Row {
                Text(
                    text = dateFormat ?: "",
                    Modifier.clearAndSetSemantics {})
                Spacer(modifier = Modifier.weight(1f))
            }
        }

    )
}

@Composable
fun StructureScreen(modifier: Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Separations.Medium, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(Separations.Medium),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            content()
        }
    }
}