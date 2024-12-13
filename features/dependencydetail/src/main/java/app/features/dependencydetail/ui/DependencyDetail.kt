package app.features.dependencydetail.ui

import app.features.dependencydetail.R
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.dependency.Dependency




@Composable
fun DependencyDetailsScreen(
    dependency: Dependency,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBackClick) {
                Text(text = stringResource(id = R.string.back_button))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.dependency_details_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }


        Image(
            painter = painterResource(id = app.base.ui.R.drawable.ic_cactus),
            contentDescription = stringResource(id = R.string.dependency_image_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Nombre:",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )
            Text(
                text = dependency.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nombre Corto:",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )
            Text(
                text = dependency.shortName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Descripción:",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )
            Text(
                text = dependency.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (!dependency.imageUrl.isNullOrBlank()) {
                Text(
                    text = "URL de Imagen:",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )
                Text(
                    text = dependency.imageUrl ?: "",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onEditClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = stringResource(id = R.string.edit_button))
        }
    }
}
@Preview
@Composable
fun PreviewDependencyDetailsScreen() {
    val sampleDependency = Dependency(
        id = "1",
        name = "Almacén Principal",
        shortName = "ALM",
        description = "Ubicado en el primer piso, cerca de la entrada.",
        imageUrl = "https://image.url",
    )

    DependencyDetailsScreen(
        dependency = sampleDependency,
        onEditClick = { println("Editar dependencia") },
        onBackClick = { println("Volver a la lista") }
    )
}




