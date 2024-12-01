package app.features.dependencyedit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.dependency.Dependency

@Composable
fun DependencyEditForm(
    dependency: Dependency,
    onSaveClick: (Dependency) -> Unit,
    onCancelClick: () -> Unit
) {

    var name by remember { mutableStateOf(dependency.name) }
    var shortName by remember { mutableStateOf(dependency.shortName) }
    var description by remember { mutableStateOf(dependency.description) }
    var imageUrl by remember { mutableStateOf(dependency.imageUrl ?: "") }

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
        Text(text = "Editar Dependencia", style = MaterialTheme.typography.headlineMedium)

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
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
                    // Solo se guarda si los datos son válidos
                    if (isValid) {
                        onSaveClick(
                            dependency.copy(
                                name = name,
                                shortName = shortName,
                                description = description,
                                imageUrl = if (imageUrl.isBlank()) null else imageUrl
                            )
                        )
                    }
                },
                enabled = isValid
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}

@Preview
@Composable
fun PreviewDependencyEditForm() {
    val sampleDependency = Dependency(
        id = "1",
        name = "Almacén Principal",
        shortName = "ALM",
        description = "Ubicado en el primer piso, cerca de la entrada.",
        imageUrl = "https://image.url"
    )

    DependencyEditForm(
        dependency = sampleDependency,
        onSaveClick = { dependency ->
            println("Dependencia actualizada: $dependency")
        },
        onCancelClick = {
            println("Edición cancelada")
        }
    )
}
