package app.features.productlist.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.Status
import app.domain.invoicing.product.Product
import app.domain.invoicing.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
@Inject constructor(private val provideProductRepository: ProductRepository) : ViewModel(){
    var state by mutableStateOf<ProductListState>(ProductListState.Loading)
        private set

    var viewState by mutableStateOf(ProductListViewState())
        private set

    var _list: List<Product> by mutableStateOf(emptyList())
        private set

    var list: List<Product> by mutableStateOf(emptyList())
        private set

    var listTags: List<String> by mutableStateOf(emptyList())

    var idProduct: Long = 0

    fun getList(){
        viewModelScope.launch {
            state = ProductListState.Loading
            provideProductRepository.getProduct().collect{ products ->
                if(products.isNotEmpty()){

                    Log.d("ProductList","Entra")
                    _list = products
                    list = _list

                    Log.d("ProductList", list.joinToString(","))
                    state = ProductListState.Success(list)
                }
                else
                    state = ProductListState.NoData

            }
        }

        listTags = list.map { it.tags.ifEmpty { "Sin Tags" } }.distinct()
    }


    fun onExpandedChange(expanded: Boolean) {
        viewState = viewState.copy(expanded = expanded)
    }

    fun onViewProduct(product: Product, navigateView: () -> Unit){
        viewModelScope.launch {
            val result = provideProductRepository.existProduct(product.code)
            idProduct = product.id
            if(result) {
                state = ProductListState.Loading
                navigateView()
            }
        }
    }

    fun onAddProduct(navigateAdd: () -> Unit){
        state = ProductListState.Loading
        navigateAdd()
    }

    fun onFilterProduct(string: String){
        //TODO("Arreglarlo por parte de los tags y el filtrado")
        val result = if (string != "Sin Tags") {
            list.filter { it.tags == string }
        } else {
            list
        }

        state = if (result.isNotEmpty()) {
            list = result
            ProductListState.Success(list)
        } else {
            ProductListState.NoData
        }
    }

    fun onAccountView(){
        //TODO("Implementar Navegación a la vista de cuenta cuando este")

    }
}
