package app.features.productdetail.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.Separations
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseImageBig
import app.base.ui.composables.BaseRow
import app.base.ui.composables.BaseStructureColumnPaddingUpSide
import app.base.ui.composables.BaseStructureCompletePadding
import app.base.ui.composables.BaseTextFieldRead
import app.base.ui.composables.TopAppBarFloatingAction
import app.features.productdetail.R


@Composable
fun ProductDetailsScreen(
    goBack: () -> Unit,
    goEdit: () -> Unit,
    viewModel: ProductDetailsViewModel,
    modifier: Modifier = Modifier,
    event: ProductDetailsEvent = ProductDetailsEvent(
    onGoEdit = viewModel::onGoEdit,
    onRemove = viewModel::removeProduct
    ))
{
    TopAppBarFloatingAction(title = viewModel.state.shortName.ifEmpty { stringResource(R.string.detailsProduct) }, goBack, {event.onGoEdit(goEdit)}, action = {event.onRemove(goBack)}, iconAction = Icons.Filled.Delete, iconFloating = Icons.Default.Edit) {
        when{
            viewModel.viewState is ProductDetailsStateView.Loading -> LoadingUi()
            else -> ProductDetails(modifier, goBack, goEdit, viewModel.state, ProductDetailsEvent(
                onGoEdit = viewModel::onGoEdit,
                onRemove = viewModel::removeProduct
            ))
        }
    }
}

data class ProductDetailsEvent(
    val onGoEdit: (() -> Unit) -> Unit = {},
    val onRemove: (() -> Unit) -> Unit = {}
)

@Composable
fun ProductDetails(modifier: Modifier = Modifier, goBack: () -> Unit, goEdit: ()-> Unit, state: ProductDetailsState, event: ProductDetailsEvent) {

        BaseStructureColumnPaddingUpSide(modifier, Separations.Medium, scrolleable = true) {
            Card {
                BaseStructureCompletePadding(modifier, Separations.Medium) {
                    BaseImageBig()
                    BaseTextFieldRead(stringResource(R.string.code), state.code)
                    BaseTextFieldRead(stringResource(R.string.name), state.name)
                    BaseTextFieldRead(stringResource(R.string.shortName), state.shortName)
                    BaseTextFieldRead(stringResource(R.string.description), state.description )
                    BaseTextFieldRead(stringResource(R.string.numSerial), state.numSerial.toString())
                    BaseTextFieldRead(stringResource(R.string.codModel), state.codModel)
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(stringResource(R.string.amount), state.amount.toString(), Modifier.weight(1f))
                        BaseTextFieldRead(stringResource(R.string.price), state.price.toString(), Modifier.weight(1f))
                    }
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.typeProduct),
                            state.typeProduct,
                            Modifier.weight(5f),
                        )
                        BaseTextFieldRead(
                            stringResource(R.string.category),
                            state.category,
                            Modifier.weight(4f),
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.section),
                            state.section,
                            Modifier.weight(1f),
                        )
                        BaseTextFieldRead(

                            stringResource(R.string.status),
                            state.status.toString(),
                            Modifier.weight(1f),
                        )
                    }
                    BaseRow(Separations.Small) {
                        BaseTextFieldRead(
                            stringResource(R.string.acquisitionDate),
                            state.acquisitionDate.toString(),
                            Modifier.weight(1f)
                        )

                        BaseTextFieldRead(
                            stringResource(R.string.cancellationDate),
                            state.cancellationDate.toString(),
                            Modifier.weight(1f)
                        )
                    }
                    BaseTextFieldRead(stringResource(R.string.notes), state.notes)

                    BaseTextFieldRead(stringResource(R.string.tags), state.tags)
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    //ProductDetails()
}