package app.features.categorydetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.features.categorydetail.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CategoryDetailsScreen(category: Category) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Detalles de la Categoría",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_logo), // Cambia ic_logo por tu recurso local
            contentDescription = "Imagen de la categoría",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nombre: ${category.name}",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp
        )
        Text(
            text = "Nombre corto: ${category.shortName}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Descripción: ${category.description}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Tipo: ${category.type.name}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Es fungible: ${if (category.isFungible) "Sí" else "No"}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        Text(
            text = "Fecha de creación: ${dateFormat.format(category.createdDate)}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview
@Composable
fun PreviewCategoryDetailsScreen() {
    val sampleCategory = Category(
        id = "1",
        name = "Electrónica",
        shortName = "ELE",
        description = "Electrodomésticos y gadgets",
        imageUrl = "https://image.url",
        createdDate = Date(),
        type = CategoryType.PREMIUM,
        isFungible = true
    )

    CategoryDetailsScreen(category = sampleCategory)
}

