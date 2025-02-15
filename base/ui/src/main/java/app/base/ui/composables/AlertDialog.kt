package app.base.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

@Composable
fun <T> DeleteObjectDialog(
    obj: T,
    name: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Eliminar ${name.lowercase()}", color = MaterialTheme.colorScheme.error) },
        //TODO(Hacer una clase abstracta para todos los modulos o pensar que hacer)
        text = { Text(text = "¿Está seguro de que desea eliminar ${name.lowercase()}: ${obj.toString()}?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}