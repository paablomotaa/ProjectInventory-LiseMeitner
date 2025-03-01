package app.features.inventorydetail.ui.productinventory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.TopAppBarTitle
import app.base.ui.composables.topappbar.Action
import app.base.ui.composables.topappbar.NavigationTopAppBar
import app.domain.invoicing.product.Product
import app.features.inventorydetail.R

data class EventsInventoryProducts(
    val ToggleSelection: (Product) -> Unit,
    val OnCheck:(Int,() -> Unit) -> Unit
)

@Composable
fun InventoryProductsList(
    onBack: () -> Unit,
    viewModel: InventoryProductsViewModel,
    inventoryId: Int
){
    val events = EventsInventoryProducts(
        ToggleSelection = viewModel::toggleProductSelection,
        OnCheck = viewModel::onCheckList
    )
    TopAppBarTitle<Nothing>(
        navigation = NavigationTopAppBar.BackPage(
            {onBack()},
            stringResource(R.string.Productos) + inventoryId,
            floating = Action.ComplexAction(
                Icons.Default.Check,
                "Confirmar",
                {events.OnCheck(inventoryId,onBack)},
                {onBack()}
            )
        ),
        content = {
            when(viewModel.state){
                InventoryProductsState.NoData ->{
                    NoDataScreen(modifier = Modifier.fillMaxSize())
                }
                InventoryProductsState.Loading ->{
                    LoadingUi()
                }
                is InventoryProductsState.Succes ->{
                    InventoryProductListContent((viewModel.state as InventoryProductsState.Succes).data,viewModel)
                }
            }
        }
    )
}

@Composable
fun InventoryProductListContent(data: List<Product>,viewModel: InventoryProductsViewModel) {
    Box(
        modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()).fillMaxSize()
    ) {
        Column {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {

                items(data) { product ->
                    val isSelected = viewModel.selectedProducts.contains(product)
                    ProductItem(product,isSelected = isSelected, onSelectionChange = {viewModel.toggleProductSelection(product)})
                }
            }
        }
    }
}
@Composable
fun ProductItem(
    inventario: Product,
    isSelected: Boolean, // Recibe el estado de selección desde el ViewModel
    onSelectionChange: (Product) -> Unit // Notifica cambios
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxSize()
            .clickable { onSelectionChange(inventario) },
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, Color.Blue) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.surface
        )
    ) {
        Row {
            Image(
                painter = painterResource(app.base.ui.R.drawable.ic_cactus),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(84.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Column {
                    Text(inventario.code)
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(inventario.description)
            }
        }
    }
}