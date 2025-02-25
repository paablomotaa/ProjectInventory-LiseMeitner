package app.features.productdetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.product.Product
import app.domain.invoicing.repositoryDB.ProductRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(val provideProductRepository: ProductRepositoryDB) : ViewModel() {
    var state by mutableStateOf(ProductDetailsState())
        private set

    var viewState by mutableStateOf<ProductDetailsStateView>(ProductDetailsStateView.Loading)
        private set


    var idProduct: Long = 0

    fun importProduct(id: Long) {
        viewModelScope.launch {
            val product = provideProductRepository.getById(id)
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

    fun onGoEdit(goEdit: () -> Unit) {
        viewModelScope.launch {
            viewState = ProductDetailsStateView.Loading
            val result = provideProductRepository.validate(state.code)
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
            val product = Product(
                id = state.id,
                code = state.code,
                name = state.name,
                shortName = state.shortName,
                description = state.description,
                numSerial = state.numSerial,
                codModel = state.codModel,
                typeProduct = state.typeProduct,
                category = state.category,
                section = state.section,
                status = state.status,
                amount = state.amount,
                price = state.price,
                image = state.image,
                acquisitionDate = state.acquisitionDate,
                cancellationDate = state.cancellationDate,
                tags = state.tags,
                notes = state.notes
            )
            provideProductRepository.remove(product)
            goBack()
        }

    }

}