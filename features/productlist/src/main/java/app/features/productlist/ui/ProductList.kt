package app.features.productlist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Dimensions
import app.base.ui.Separations
import app.base.ui.composables.BaseImageSmall
import app.base.ui.composables.BaseStructureCompletePadding
import app.base.ui.composables.CardRow
import app.base.ui.composables.TopAppBarComplete
import app.domain.invoicing.product.Product

/*
//region prueba
val product1 = Product(
    3,
    "fw",
    "rqr",
    "rwr",
    "fef",
    3.0,
    "fef",
    "fef",
    "fef",
    Status.NEW,
    3,
    3.0,
    "fef",
    Date(),
    Date(),
    "da",
    "afwf"
)
val product2 = Product(
    5,
    "fw",
    "FFW",
    "rwr",
    "fef",
    3.0,
    "fef",
    "fef",
    "fef",
    Status.NEW,
    3,
    3.0,
    "fef",
    Date(),
    Date(),
    "da",
    "afwf"
)

val lista = listOf(
    product1,
    product2,
    product1,
    product2,
    product1,
    product2,
    product1,
    product2,
    product1,
    product2,
    product1,
    product2,
    product1,
    product2,
)
//endregion
*/
@Preview(showBackground = true)
@Composable
fun ProductList(modifier: Modifier = Modifier) {

    var codigo = rememberSaveable { mutableStateListOf("") }
    var nameInventory = rememberSaveable { mutableStateOf("") }
    var listProduct = rememberSaveable { mutableStateOf<List<Product>>(emptyList()) }

    TopAppBarComplete(title = nameInventory.value) {
        BaseStructureCompletePadding(modifier, Separations.Zero, scrolleable = false) {
            MessageList(listProduct.value)
        }

    }
}

@Composable
fun ProductItem(product: Product) {
    CardRow(onClick = {/*TODO*/ }) {
        BaseImageSmall()
        Text(
            text = product.name,
            textAlign = TextAlign.Center,
            fontSize = Dimensions.Small,
            modifier = Modifier
                .padding(Separations.Medium)
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

    }
}

@Composable
fun MessageList(product: List<Product>) {
    LazyColumn {
        product.forEach { product ->
            item {
                ProductItem(product)
            }
        }

    }
}