package app.features.inventorylist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
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
import app.domain.invoicing.inventory.Inventory
import app.features.inventorylist.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.LaunchedEffect
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.DeleteObjectDialog
import app.base.ui.composables.TopAppBarTitle
import app.base.ui.composables.topappbar.Action
import app.base.ui.composables.topappbar.NavigationTopAppBar

data class eventInventoryList(
    val onViewInventory: (Inventory,(Inventory)->Unit) -> Unit,
    val onExpandeChange: (Boolean) -> Unit,
    val onAddInventory: (() ->Unit) -> Unit,
    val onEditInventory:(Inventory,()->Unit) -> Unit,
    val onFilterInventory:(String) -> Unit,
    val onAccountView:() -> Unit,
    val onDelete:(Inventory) -> Unit
)

/**
 *
 * Inventory List
 *
 * Interfaz de Inventory List
 *
 */

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
        onExpandeChange = viewModel::onExpandedChange,
        onDelete = viewModel::onDelete
    )
    LaunchedEffect(Unit){
        viewModel.getList()
    }
    TopAppBarTitle<Nothing>(
        navigation = NavigationTopAppBar.OptDrawer(onClick = onOpenDrawer, label = stringResource(R.string.Titulo),
            floating = Action.ComplexAction(
                icon = Icons.Default.Add,
                label = "Añadir",
                onClick = events.onAddInventory,
                onGo = goAdd
            )
        )

    ) {
        when (viewModel.state) {
            InventoryListState.NoData -> {
                NoDataScreen(modifier)
            }

            is InventoryListState.Succes -> {
                InventoryListContent(
                    goAdd,
                    viewmodel = viewModel,
                    events = events,
                    inventories = (viewModel.state as InventoryListState.Succes).data,
                    onOpenDrawer = onOpenDrawer,
                    goDetails = goDetails
                )
            }

            InventoryListState.Loading -> {
                LoadingUi()
            }
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

        Box(
            modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()).fillMaxSize()
        ) {
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(inventories) { inventario ->
                            InventoryItem(events,inventario,goDetails,goDelete = {selected -> viewmodel.productDelete = selected})
                        }
                    }
                }
            }
    if(viewmodel.productDelete != null){
        DeleteObjectDialog(
            obj = viewmodel.productDelete!!,
            onConfirm = {
                events.onDelete(viewmodel.productDelete!!)
                viewmodel.productDelete = null //Aqui ya se ha eliminado
            },
            onDismiss = {viewmodel.productDelete = null }, //Anulo la operación onLongClick
            name = viewmodel.productDelete!!.name
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InventoryItem(
    events: eventInventoryList,
    inventario: Inventory,
    goDetails: (Inventory) -> Unit,
    goDelete: (Inventory) -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxSize().combinedClickable (
                onClick = { events.onViewInventory(inventario,{goDetails(inventario)}) },
                onLongClick = {goDelete(inventario)}
            ),
        shape = RoundedCornerShape(16.dp),

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
@Preview
@Composable
fun InventoryListPreview(){

}