package app.features.inventorydetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.BaseTextFieldRead
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.SmallSpace
import app.base.ui.composables.TopAppBarTitle
import app.base.ui.composables.topappbar.Action
import app.base.ui.composables.topappbar.NavigationTopAppBar
import app.domain.invoicing.inventory.Inventory
import app.features.inventorydetail.R
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

data class InventoryDetailsEvents(
    val getInventoryInfo: KFunction1<Int, Unit>,
    val goEdit: KFunction2<Int, () -> Unit, Unit>,
)

@Composable
fun InventoryDetails(
    onBack: () -> Unit,
    inventoryId: Int,
    viewModel: InventoryDetailsViewModel,
    goEdit: () -> Unit
) {
    val events = InventoryDetailsEvents(
        getInventoryInfo = viewModel::getInventoryInfo,
        goEdit = viewModel::onGoEdit
    )
    val inventoryid2 = inventoryId
    viewModel.getInventoryInfo(inventoryid2)
    when(viewModel.state) {
        InventoryDetailsState.NoData -> {
            NoDataScreen(modifier = Modifier.fillMaxSize())
        }
        InventoryDetailsState.Loading -> {
            LoadingUi()
        }
        is InventoryDetailsState.Success -> {
            DetailsScreen(onBack, (viewModel.state as InventoryDetailsState.Success).inventory, goEdit, events)
        }
    }
}

@Composable
fun DetailsScreen(
    onBack: () -> Unit,
    inventory: Inventory,
    goEdit: () -> Unit,
    events: InventoryDetailsEvents
) {
    TopAppBarTitle<Nothing>(
        navigation = NavigationTopAppBar.BackPage(
            { onBack() },
            stringResource(R.string.Detalles),
            floating = Action.ComplexAction(
                Icons.Default.Edit,
                "Editar Producto",
                { events.goEdit(inventory.id, goEdit) },
                goEdit
            )
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BaseTextFieldRead(
                    text = stringResource(R.string.Codigo),
                    value = inventory.code
                )
                MediumSpace()
                BaseTextFieldRead(
                    text = stringResource(R.string.Nombre),
                    value = inventory.name
                )
                MediumSpace()
                BaseTextFieldRead(
                    text = stringResource(R.string.Descripcion),
                    value = inventory.description
                )
                MediumSpace()
                BaseTextFieldRead(
                    text = stringResource(R.string.Tipo),
                    value = inventory.type
                )
                MediumSpace()
                BaseTextFieldRead(
                    text = stringResource(R.string.FechActivo),
                    value = inventory.dateActive.toString()
                )
                MediumSpace()
                //TODO Implementar la vista de la lista de los productos de cada inventario
                Text(
                    text = stringResource(R.string.Productos),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                Column {
                    Card(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxWidth(),
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
                                    Text("Product")
                                }
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text("Description")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun InventoryDetailsPreview(){

}