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
import kotlinx.coroutines.launch

class ProductListViewModel: ViewModel(){
    var state by mutableStateOf<ProductListState>(ProductListState.Loading)
        private set

    var viewState by mutableStateOf(ProductListViewState())
        private set

    var _list: List<Product> by mutableStateOf(emptyList())
        private set

    var list: List<Product> by mutableStateOf(emptyList())
        private set

    var listTags: List<String> by mutableStateOf(emptyList())

    fun getList(){
        viewModelScope.launch {
            state = ProductListState.Loading
            ProductRepository.getProduct().collect{ products ->
                if(products.isNotEmpty()){
                    _list = products
                    list = _list
                    state = ProductListState.Success(list)
                }
                else
                    state = ProductListState.NoData

            }
        }

        Log.d("Lista", list.map { it.tags }. toString())
        listTags = list.map { it.tags.ifEmpty { "Sin Tags" } }.distinct()
    }

    fun onExpandedChange(expanded: Boolean) {
        viewState = viewState.copy(expanded = expanded)
    }

    fun onViewProduct(product: Product, navigateView: () -> Unit){
        viewModelScope.launch {
            val result = ProductRepository.existProduct(product.code)
            if(result)
                //TODO("Implementar Navegación a vista producto")
                navigateView()
        }
    }

    fun onAddProduct(navigateAdd: () -> Unit){
        state = ProductListState.Loading
        navigateAdd()
    }

    fun onFilterProduct(string: String){
        val result = if (string != "Sin Tags") {
            list.filter { it.tags == string }
        } else {
            list
        }

        state = if (result.isNotEmpty()) {
            ProductListState.Success(result)
        } else {
            ProductListState.NoData
        }
    }

    fun onAccountView(){
        //TODO("Implementar Navegación a la vista de cuenta cuando este")

    }
}
