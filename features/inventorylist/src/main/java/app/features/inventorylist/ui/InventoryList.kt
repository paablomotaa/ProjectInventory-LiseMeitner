package app.features.inventorylist.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.TopAppBarNormal
import app.domain.invoicing.inventory.Inventory
import app.features.inventorylist.R
import androidx.compose.foundation.lazy.items
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen

data class eventInventoryList(
    val onViewInventory: (Inventory,(Inventory)->Unit) -> Unit,
    val onExpandeChange: (Boolean) -> Unit,
    val onAddInventory: (() ->Unit) -> Unit,
    val onEditInventory:(Inventory,()->Unit) -> Unit,
    val onFilterInventory:(String) -> Unit,
    val onAccountView:() -> Unit
)

@Composable
fun InventoryListScreen(
    goAdd: () -> Unit,
    viewModel: InventoryListViewModel,
    onOpenDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    goDetails: (Inventory) -> Unit
){
    val events = eventInventoryList(
        onViewInventory = viewModel::onViewInventory,
        onAddInventory = viewModel::onAddInventory,
        onAccountView = viewModel::onAccountView,
        onFilterInventory = viewModel::onFilterInventory,
        onEditInventory = viewModel::onEditInventory,
        onExpandeChange = viewModel::onExpandedChange
    )
    when(viewModel.state){
        InventoryListState.NoData ->{
            NoDataScreen(modifier)
        }
        is InventoryListState.Succes->{
            InventoryListContent(
                goAdd,
                viewmodel = viewModel,
                events = events,
                inventories = (viewModel.state as InventoryListState.Succes).data,
                onOpenDrawer = onOpenDrawer,
                goDetails = goDetails
            )
        }
        InventoryListState.Loading->{
            LoadingUi()
        }
    }
}

@Composable
fun InventoryListContent(
    goAdd:() -> Unit,
    modifier: Modifier = Modifier,
    viewmodel:InventoryListViewModel,
    events:eventInventoryList,
    inventories:List<Inventory>,
    onOpenDrawer: () -> Unit,
    goDetails: (Inventory) -> Unit
)
{
    TopAppBarNormal(
        title = stringResource(R.string.Titulo),
        expandedValue = viewmodel.state.expanded,
        goAdd = goAdd,
        onAdd = events.onAddInventory,
        listFilter = viewmodel.listtypes,
        onAccount = onOpenDrawer,
        onExpandedChange = events.onExpandeChange,
        onFilter = events.onFilterInventory,
    ) {
        Box(
            modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()).fillMaxSize()
        ) {
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(inventories) { inventario ->
                            Card(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                                    .fillMaxSize(),
                                shape = RoundedCornerShape(16.dp),
                                onClick = { events.onViewInventory(inventario,{goDetails(inventario)}) }
                            ) {
                                Row {
                                    Image(
                                        painter = painterResource(app.base.ui.R.drawable.ic_cactus),
                                        contentDescription = null,
                                        modifier = Modifier.padding(8.dp).size(84.dp).clip(
                                            RoundedCornerShape(corner = CornerSize(16.dp))
                                        )
                                    )
                                    Column(
                                        modifier = Modifier.padding(16.dp).fillMaxWidth()
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

                    }
                }
            }
        }
    }
@Preview
@Composable
fun InventoryListPreview(){

}