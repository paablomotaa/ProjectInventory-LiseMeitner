package app.features.categorydetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            text = stringResource(R.string.category_details_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = app.base.ui.R.drawable.ic_cactus),
            contentDescription = stringResource(R.string.category_image_description),
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.category_name, category.name),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.category_short_name, category.shortName),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.category_description, category.description),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.category_type, category.type.name),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(
                R.string.category_is_fungible,
                stringResource(if (category.isFungible) R.string.category_is_fungible_yes else R.string.category_is_fungible_no)
            ),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        Text(
            text = stringResource(R.string.category_created_date, dateFormat.format(category.createdDate)),
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

