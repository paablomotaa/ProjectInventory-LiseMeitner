package app.features.dependencylist.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.dependency.Dependency
import app.features.dependencylist.R


/*@Composable
fun DependencyListScreen(
    dependencies: List<Dependency>,
    onDependencyClick: (Dependency) -> Unit,
    onAddClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(dependencies) { dependency ->
                DependencyItem(
                    dependency = dependency,
                    onClick = { onDependencyClick(dependency) }
                )
            }
        }

        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_dependency_button)
            )
        }
    }
}

@Composable
fun DependencyItem(
    dependency: Dependency,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = app.base.ui.R.drawable.ic_cactus),
                contentDescription = stringResource(id = R.string.dependency_image_content_description),
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = dependency.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dependency.shortName,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDependencyListScreen() {
    val sampleDependencies = listOf(
        Dependency(
            id = "1",
            name = "Almacén Principal",
            shortName = "ALM",
            description = "Ubicado en el primer piso.",
            imageUrl = "https://image.url"
        ),
        Dependency(
            id = "2",
            name = "Oficina Central",
            shortName = "OFI",
            description = "Edificio principal, segundo piso.",
            imageUrl = "https://image.url"
        )
    )

    DependencyListScreen(
        dependencies = sampleDependencies,
        onDependencyClick = { dependency -> println("Clic en: ${dependency.name}") },
        onAddClick = { println("Añadir nueva dependencia") }
    )
}
*/