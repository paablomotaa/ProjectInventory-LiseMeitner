package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.BaseTextFieldRead
import app.base.ui.composables.TopAppBarTitle
import app.domain.invoicing.inventory.Inventory
import app.features.inventorydetail.R
import kotlin.reflect.KFunction1

data class InventoryDetailsEvents(
    val getInventoryInfo: KFunction1<Int?, Unit>,
)

@Composable
fun InventoryDetails(onBack:() -> Unit, inventoryId: Int?, viewModel:InventoryDetailsViewModel){
    val events = InventoryDetailsEvents(
        getInventoryInfo = viewModel::getInventoryInfo
    )
    val inventoryid2 = inventoryId
    viewModel.getInventoryInfo(inventoryid2)
    when(viewModel.state){
        InventoryDetailsState.NoData ->{
            NoDataScreen(modifier = Modifier)
        }
        InventoryDetailsState.Loading ->{
            LoadingUi()
        }

        is InventoryDetailsState.Success -> {
            DetailsScreen(onBack,(viewModel.state as InventoryDetailsState.Success).inventory)
        }
    }
}
@Composable
fun DetailsScreen(onBack: () -> Unit,inventory: Inventory){
    TopAppBarTitle(
        title = stringResource(R.string.Detalles),
        onBack = onBack,
        content = {Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BaseTextFieldRead(stringResource(R.string.Codigo), inventory.code)
            BaseTextFieldRead(stringResource(R.string.Nombre), inventory.name)
            BaseTextFieldRead(stringResource(R.string.Descripcion), inventory.description)
            BaseTextFieldRead(stringResource(R.string.Tipo), inventory.type.toString())
            BaseTextFieldRead(stringResource(R.string.FechActivo), inventory.dateActive.toString())
        }},
    )
}

@Preview
@Composable
fun InventoryDetailsPreview(){

}