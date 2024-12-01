package app.features.dependencycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.dependency.Dependency


@Composable
fun DependencyCreateForm(
    onSaveClick: (Dependency) -> Unit,
    onCancelClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var shortName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    // Validación en tiempo real
    val isValid = remember(name, shortName, description) {
        name.isNotBlank() && description.isNotBlank() &&
                shortName.length >= 3 && shortName.all { it.isLetterOrDigit() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Crear Dependencia", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        TextField(
            value = shortName,
            onValueChange = { shortName = it },
            label = { Text("Nombre Corto") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("URL de Imagen") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = onCancelClick) {
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onSaveClick(
                        Dependency(
                            id = "", // ID generado automáticamente en backend
                            name = name,
                            shortName = shortName,
                            description = description,
                            imageUrl = if (imageUrl.isBlank()) null else imageUrl
                        )
                    )
                },
                enabled = isValid
            ) {
                Text("Guardar")
            }
        }
    }
}


@Preview
@Composable
fun PreviewDependencyCreateForm() {
    val sampleDependency = Dependency(
        id = "1",
        name = "Almacén Principal",
        shortName = "ALM",
        description = "Ubicado en el primer piso, cerca de la entrada.",
        imageUrl = "https://image.url",
    )

    DependencyCreateForm (
        onSaveClick = { dependency ->
            println("Dependencia guardada: $dependency")
        },
        onCancelClick = {
            println("Creación cancelada")
        }
    )
}
