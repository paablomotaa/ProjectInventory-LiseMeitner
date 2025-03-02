package app.base.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.base.ui.Separations
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun DialogDate2(showDialog: Boolean, onShowDialogChange: (Boolean) -> Unit, onDateChange: (LocalDate) -> Unit){
    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { onShowDialogChange(false) },
            onDateSelected = { date -> onDateChange(date) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog2(
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest,properties = DialogProperties( usePlatformDefaultWidth = false )) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = Separations.VerySmall
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val datePickerState = rememberDatePickerState()
                DatePicker(state = datePickerState, )

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
                                val selectedDate = LocalDate.ofEpochDay(selectedDateMillis / (24 * 60 * 60 * 1000))
                                onDateSelected(selectedDate)
                            }
                            onDismissRequest()
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}