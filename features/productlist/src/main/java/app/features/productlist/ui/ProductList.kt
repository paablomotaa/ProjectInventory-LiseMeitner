package app.features.productlist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Dimensions
import app.base.ui.Separations
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.BaseImageSmall
import app.base.ui.composables.BaseStructureCompletePadding
import app.base.ui.composables.CardRow
import app.base.ui.composables.TopAppBarComplete
import app.domain.invoicing.product.Product

@Composable
fun ProductListScreen(
    goBack: () -> Unit,
    goAdd: () -> Unit,
    goView: () -> Unit,
    viewModel: ProductListViewModel,
    modifier: Modifier = Modifier, event: EventProductList = EventProductList(
            onViewProduct = viewModel::onViewProduct,
            onAddProduct = viewModel::onAddProduct,
            onFilterProduct = viewModel::onFilterProduct,
            onAccountView = viewModel::onAccountView,
            onExpandadChange = viewModel::onExpandedChange
        )){
    var nameInventory = rememberSaveable { mutableStateOf("Inventario") }

    TopAppBarComplete(title = nameInventory.value, viewModel.viewState.expanded, event.onExpandadChange, viewModel.listTags, goBack, event.onFilterProduct, event.onAddProduct, goAdd, event.onAccountView) {
        when(viewModel.state){
            is ProductListState.NoData ->{NoDataScreen(modifier)}
            is ProductListState.Loading ->{
                LoadingUi()
            }
            is ProductListState.Success ->{
                ProductList(goView, viewModel.list, viewModel, modifier, event)
            }
        }
    }
}

data class EventProductList(
    //Uso {_,_ ->} para que no de error de 'Expected 2 parameters of types Product, () -> Unit'
    val onViewProduct: (Product, () -> Unit) -> Unit = {_,_ ->},
    val onAddProduct: (() -> Unit) -> Unit = {},
    val onFilterProduct: (String) -> Unit = {},
    val onBackProduct: (()-> Unit) -> Unit = {},
    val onAccountView: () -> Unit = {},
    val onExpandadChange: (Boolean) -> Unit = {}
)

@Composable
fun ProductList(
    goView: () -> Unit,
    listProduct: List<Product>,
    viewModel: ProductListViewModel,
    modifier: Modifier,
    event: EventProductList
) {

    //TODO:  Cambiar el titulo

    BaseStructureCompletePadding(modifier, Separations.Zero, scrolleable = false) {
        MessageList(viewModel, listProduct, goView, event)
    }
}

@Composable
fun ProductItem(product: Product, goView: () -> Unit, event: EventProductList) {
    CardRow(onClick = {event.onViewProduct(product, goView)}) {
        BaseImageSmall()
        Text(
            text = product.name,
            textAlign = TextAlign.Center,
            fontSize = Dimensions.Small,
            modifier = Modifier
                .padding(Separations.Small)
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

    }
}

@Composable
fun MessageList(viewModel: ProductListViewModel, product: List<Product>, goView: () -> Unit, event: EventProductList) {
    LazyColumn {
        product.forEach { product ->
            item {
                ProductItem(product, goView, event)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val viewModel = remember{ProductListViewModel()}
    viewModel.getList()
    ProductListScreen({}, {}, {}, viewModel)

}