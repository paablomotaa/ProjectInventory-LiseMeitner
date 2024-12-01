package app.features.categorylist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryType
import app.features.categorylist.R
import java.util.Date

@Composable
fun CategoryListScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    onFabClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(categories) { category ->
                CategoryItem(category = category, onClick = { onCategoryClick(category) })
            }
        }

        FloatingActionButton(
            onClick = onFabClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Add,
                contentDescription = stringResource(id = R.string.fab_create_category)
            )
        }
    }
}


@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = app.base.ui.R.drawable.ic_cactus),
                contentDescription = stringResource(id = R.string.category_logo_content_description),
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = category.name,
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit_category_content_description),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        // TODO acceso a details
                    }
            )
        }
    }
}


@Preview
@Composable
fun PreviewCategoryListWithScroll() {
    // Lista de ejemplo
    val sampleCategories = listOf(
        Category(
            id = "1", name = "Electrónica", shortName = "ELE",
            description = "Electrodomésticos y gadgets", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.PREMIUM, isFungible = false
        ),
        Category(
            id = "2", name = "Oficina", shortName = "OFI",
            description = "Suministros de oficina y papelería", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.BASICO, isFungible = true
        ),
        Category(
            id = "3", name = "Hogar", shortName = "HOG",
            description = "Artículos para el hogar", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.ECOLOGICO, isFungible = true
        ),
        Category(
            id = "4", name = "Deportes", shortName = "DEP",
            description = "Artículos deportivos", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.ECONOMICO, isFungible = true
        ),
        Category(
            id = "5", name = "Moda", shortName = "MOD",
            description = "Ropa y accesorios", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.PREMIUM, isFungible = false
        ),
        Category(
            id = "6", name = "Alimentos", shortName = "ALI",
            description = "Productos alimenticios", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.BASICO, isFungible = true
        ),
        Category(
            id = "7", name = "Juguetes", shortName = "JUG",
            description = "Juegos y juguetes para niños", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.ECOLOGICO, isFungible = true
        ),
        Category(
            id = "8", name = "Tecnología", shortName = "TEC",
            description = "Productos tecnológicos avanzados", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.PREMIUM, isFungible = false
        ),
        Category(
            id = "9", name = "Salud", shortName = "SAL",
            description = "Productos para el bienestar", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.BASICO, isFungible = true
        ),
        Category(
            id = "10", name = "Viajes", shortName = "VIA",
            description = "Accesorios y servicios para viajeros", imageUrl = "https://image.url",
            createdDate = Date(), type = CategoryType.ECONOMICO, isFungible = true
        )
    )
    CategoryListScreen(
        categories = sampleCategories,
        onCategoryClick = { category ->
            println("Clic en la categoría: ${category.name}")
        },
        onFabClick = { }
    )
}







