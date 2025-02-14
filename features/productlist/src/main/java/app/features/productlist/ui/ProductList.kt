package app.features.productlist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Dimensions
import app.base.ui.Separations
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.BaseImageSmall
import app.base.ui.composables.BaseStructureCompletePaddingNoCenter
import app.base.ui.composables.CardRow
import app.base.ui.composables.DeleteObjectDialog
import app.base.ui.composables.TopAppBarTitle
import app.base.ui.composables.topappbar.Action
import app.base.ui.composables.topappbar.NavigationTopAppBar
import app.domain.invoicing.product.Product
import app.features.productlist.R
import com.example.login.base.icon_composable.filter

@Composable
fun ProductListScreen(
    goBack: () -> Unit,
    goAdd: () -> Unit,
    goView: () -> Unit,
    viewModel: ProductListViewModel,
    onOpenDrawer: () -> Unit ,
    modifier: Modifier = Modifier, event: EventProductList = EventProductList(
            onViewProduct = viewModel::onViewProduct,
            onAddProduct = viewModel::onAddProduct,
            onFilterProduct = viewModel::onFilterProduct,
            onExpandadChange = viewModel::onExpandedChange,
            onDelete = viewModel::onDeleteProduct
        )){
    //TODO("Cambiar el titulo por el nombre del inventario)
    var nameInventory = rememberSaveable { mutableStateOf("Producto") }

    LaunchedEffect(Unit){
        viewModel.getList()
    }

    TopAppBarTitle(
        navigation = NavigationTopAppBar.OptDrawer(
            {onOpenDrawer()},
            nameInventory.value,
            listOf(
                Action.SimpleAction(
                    filter(),
                    stringResource(id = R.string.filter),
                    {event.onExpandadChange(true)}
                ),
                Action.DropDown(
                    viewModel.viewState.expanded,
                    event.onExpandadChange,
                    viewModel.listTags,
                    event.onFilterProduct)
            ),
            Action.ComplexAction(
                Icons.Default.Add,
                stringResource(id = R.string.add),
                event.onAddProduct,
                goAdd
            )
        )
    )
    {

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
    val onExpandadChange: (Boolean) -> Unit = {},
    val onDelete: (Product) -> Unit = {}
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

    BaseStructureCompletePaddingNoCenter(modifier, Separations.Zero, scrolleable = false) {
        MessageList(viewModel, listProduct, goView, event)
    }
}

@Composable
fun ProductItem(product: Product, goView: () -> Unit, goDelete: (Product)-> Unit, event: EventProductList) {
    CardRow(onClick = {event.onViewProduct(product, goView)}, onLongClick = {goDelete(product)}) {
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
                ProductItem(
                    product,
                    goView,
                    goDelete = { selectedproduct ->
                        viewModel.productDelete = selectedproduct
                    },
                    event)
            }
        }
    }
    if(viewModel.productDelete != null){
        DeleteObjectDialog(
            obj = viewModel.productDelete!!,
            onConfirm = {
                event.onDelete(viewModel.productDelete!!)
                viewModel.productDelete = null //Aqui ya se ha eliminado
            },
            onDismiss = {viewModel.productDelete = null }, //Anulo la operación onLongClick
            name = stringResource(id = R.string.product)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    //val viewModel = remember{ProductListViewModel()}
    //viewModel.getList()
    //ProductListScreen({}, {}, {}, viewModel,{})

}