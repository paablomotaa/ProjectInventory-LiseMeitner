package app.features.categorylist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.domain.invoicing.category.Category
//import app.domain.navigation.CategoryGraph
import app.features.categorylist.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
//import app.domain.navigation.categoryGraph


@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel,
    navController: NavController
) {
    val state = viewModel.state

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    // Error al cargar
    else if (state.isError) {
        AlertDialog(
            onDismissRequest = { viewModel.fetchCategories() },  // Recargar categorías
            confirmButton = {
                TextButton(onClick = { viewModel.fetchCategories() }) {
                    Text(stringResource(id = R.string.accept))
                }
            },
            title = {
                Text(text = stringResource(id = R.string.error))
            },
            text = {
                Text(text = state.errorMessage ?: stringResource(id = R.string.unknown_error))
            }
        )
    }
    // No hay categorías disponibles
    else if (state.isEmpty) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_categories_available),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
    // Mostrar lista de categorías
    else {
        CategoryListContent(
            categories = state.categories,
            onCategoryClick = { category ->
                print("Boton de Details")
            },
            onFabClick = {
                // Navegar a la pantalla para crear categoría
                //navController.navigate(CategoryGraph.categoryCreate())
            },
            onDeleteClick = { category ->
                // Lógica para eliminar la categoría
                viewModel.deleteCategory(category)
            },
            onEditClick = { category ->
                // Navegar a la pantalla de editar categoría
                print("Boton de Edit")
            }
        )
    }
}

@Composable
fun CategoryListContent(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    onFabClick: () -> Unit,
    onDeleteClick: (Category) -> Unit,
    onEditClick: (Category) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = { onCategoryClick(category) },
                    onDeleteClick = { onDeleteClick(category) },
                    onEditClick = { onEditClick(category) }
                )
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
fun CategoryItem(
    category: Category,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: (Category) -> Unit
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(category.imageUrl.ifEmpty { null })
            .apply {
                crossfade(true)
                error(app.base.ui.R.drawable.ic_cactus)
                placeholder(app.base.ui.R.drawable.ic_cactus)
            }
            .build()
    )

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
                painter = imagePainter,
                contentDescription = stringResource(id = R.string.category_logo_content_description),
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
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
                    .padding(end = 16.dp)
                    .clickable {
                        onEditClick(category)
                    }
            )

            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_category_content_description),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onDeleteClick()
                    }
            )
        }
    }
}



@Preview
@Composable
fun PreviewCategoryListScreen() {
    val navController = rememberNavController()

    // Asegúrate de envolver en un NavHost para que las rutas funcionen
    /*NavHost(
        navController = navController,
        startDestination = CategoryGraph.categoryList()
    ) {
        categoryGraph(navController) // Llamar al gráfico de navegación
    }*/
}








