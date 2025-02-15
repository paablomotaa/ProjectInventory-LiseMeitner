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
        private set

    var idProduct: Long = 0
    var productDelete by mutableStateOf<Product?>(null)

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
                    listTags = list.map { it.tags.takeIf { it.isNotEmpty() } ?: "Sin Tags" }.plus("Sin Tags").distinct()
                }
                else
                    state = ProductListState.NoData

            }
        }

    }


    fun onExpandedChange(expanded: Boolean) {
        viewState = viewState.copy(expanded = expanded)
    }

    fun onViewProduct(product: Product, navigateView: () -> Unit){
        viewModelScope.launch {
            state = ProductListState.Loading
            val result = provideProductRepository.existProduct(product.code)
            idProduct = product.id
            if(result) {
                navigateView()
            }
            else{
                getList()
            }
        }
    }

    fun onAddProduct(navigateAdd: () -> Unit){
        state = ProductListState.Loading
        navigateAdd()
    }

    fun onFilterProduct(string: String){
        viewState = viewState.copy(expanded = false)
        //TODO("Arreglarlo por parte de los tags y el filtrado")
        viewModelScope.launch {
            state = ProductListState.Loading
            val result = if (string != "Sin Tags") {
                _list.filter { it.tags == string }
            } else {
                _list
            }

            if (result.isNotEmpty()) {
                list = result
                state = ProductListState.Success(list)
            } else {
                state = ProductListState.NoData
            }
        }
    }

    fun onDeleteProduct(product: Product){
        viewModelScope.launch {
            state = ProductListState.Loading
            provideProductRepository.deleteProduct(product.id)
            getList()
        }
    }
}
