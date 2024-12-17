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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import app.domain.invoicing.category.Category
import app.features.categorylist.R




@Composable
fun CategoryListScreen(goAdd: () -> Unit, viewModel: CategoryListViewModel, modifier: Modifier = Modifier, onOpenDrawer: () -> Unit) {
    val events = CategoryListEvents(
        onCategoryClick = { },
        onFabClick = { goAdd() },
        onDeleteClick = { category -> viewModel.onDeleteCategory(category) },
        onEditClick = {  },
        onRetry = { viewModel.fetchCategories() }
    )

    when (val state = viewModel.state) {
        is CategoryListState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is CategoryListState.Error -> {
            AlertDialog(
                onDismissRequest = events.onRetry,
                confirmButton = {
                    TextButton(onClick = events.onRetry) {
                        Text(stringResource(id = R.string.accept))
                    }
                },
                title = { Text(text = stringResource(id = R.string.error)) },
                text = { Text(text = state.errorMessage ?: stringResource(id = R.string.unknown_error)) }
            )
        }
        is CategoryListState.NoData -> {
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
        is CategoryListState.Success -> {
            CategoryListContent(
                goAdd,
                categories = state.categories,
                events = events
            )
        }
    }
}

@Composable
fun CategoryListContent(goAdd: () -> Unit, categories: List<Category>, events: CategoryListEvents) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = { events.onCategoryClick(category) },
                    onDeleteClick = { events.onDeleteClick(category) },
                    onEditClick = { events.onEditClick(category) }
                )
            }
        }

        FloatingActionButton(
            onClick = events.onFabClick,
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
fun CategoryItem(category: Category, onClick: () -> Unit, onDeleteClick: () -> Unit, onEditClick: (Category) -> Unit) {
    val image = if (category.imageUrl.isNotEmpty()) {
        app.base.ui.R.drawable.ic_cactus
    } else {
        app.base.ui.R.drawable.ic_cactus
    }

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
                painter = painterResource(id = image),
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
                    .size(40.dp)
                    .padding(end = 16.dp)
                    .clickable { onEditClick(category) }
            )

            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_category_content_description),
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onDeleteClick() }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategoryListScreen() {
    val navController = rememberNavController()
    val viewModel = CategoryListViewModel()
    CategoryListScreen({}, viewModel,onOpenDrawer = {})
}










