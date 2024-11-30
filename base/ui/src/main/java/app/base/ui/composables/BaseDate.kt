package app.base.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.base.ui.Separations
import app.base.utils.dateValidation
import java.time.LocalDate

@Composable
fun DateField(
    showDialog: MutableState<Boolean>,
    selectedDate: MutableState<String>,
    isDateError: MutableState<Boolean>,
    text: String,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        isError = isDateError.value,
        singleLine = true,
        value = selectedDate.value,
        label = { Text(text) },
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }

        },
        supportingText = {
            Row {
                Text(
                    text = if (isDateError.value) "Fecha muy grande" else "",
                    Modifier.clearAndSetSemantics {})
                Spacer(modifier = Modifier.weight(1f))
            }
        }

    )
}

@Composable
fun DialogDate(
    showDialog: MutableState<Boolean>,
    selectedDate: MutableState<String>,
    isDateError: MutableState<Boolean>
) {
    if (showDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDialog.value = false },
            onDateSelected = { date ->
                selectedDate.value = date.toString()
                isDateError.value = !dateValidation(selectedDate.value)
                showDialog.value = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = Separations.VerySmall
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val datePickerState = rememberDatePickerState()
                DatePicker(state = datePickerState)

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = {
                            val selectedDateMillis = datePickerState.selectedDateMillis
                            if (selectedDateMillis != null) {
                                val selectedDate =
                                    LocalDate.ofEpochDay(selectedDateMillis / (24 * 60 * 60 * 1000))
                                onDateSelected(selectedDate)
                            }
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}