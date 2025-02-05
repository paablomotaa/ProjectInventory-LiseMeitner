package app.features.productdetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(val provideProductRepository: ProductRepository) : ViewModel() {
    var state by mutableStateOf(ProductDetailsState())
        private set

    var viewState by mutableStateOf<ProductDetailsStateView>(ProductDetailsStateView.Loading)
        private set


    var idProduct: Long = 0

    fun importProduct(id: Long) {
        viewModelScope.launch {
            provideProductRepository.findProduct(id).collect{ product ->
                if(product != null){
                    state = state.copy(
                        id = product.id,
                        code = product.code,
                        name = product.name,
                        shortName = product.shortName,
                        description = product.description,
                        numSerial = product.numSerial,
                        codModel = product.codModel,
                        typeProduct = product.typeProduct,
                        category = product.category,
                        section = product.section,
                        status = product.status,
                        amount = product.amount,
                        price = product.price,
                        image = product.image,
                        acquisitionDate = product.acquisitionDate,
                        cancellationDate = product.cancellationDate,
                        tags = product.tags,
                        notes = product.notes,
                        isLoading = false
                    )
                    viewState = ProductDetailsStateView.Success
                }
            }
        }
    }

    fun onGoEdit(goEdit: () -> Unit) {
        viewModelScope.launch {
            viewState = ProductDetailsStateView.Loading
            val result = provideProductRepository.existProduct(state.code)
            idProduct = state.id
            if(result){
                goEdit()
            }
            else{
                viewState = ProductDetailsStateView.Success
            }
        }
    }

    fun removeProduct(goBack: () -> Unit) {
        viewModelScope.launch {
            viewState = ProductDetailsStateView.Loading
            provideProductRepository.deleteProduct(state.id)
            goBack()
        }

    }

}