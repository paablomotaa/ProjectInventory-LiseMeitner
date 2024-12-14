package app.features.productlist.ui

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

    var list: List<Product> by mutableStateOf(emptyList())
        private set

    var listTags: List<String> by mutableStateOf(listOf("Sin Tags"))

    fun getList(){
        viewModelScope.launch {
            state = ProductListState.Loading
            ProductRepository.getProduct().collect{ products ->
                if(products.isNotEmpty()){
                    list = products
                    state = ProductListState.Success(list)
                    listTags = list.map { it.tags } + ("Sin Tags")
                }
                else
                    state = ProductListState.NoData

            }
        }
    }

    fun onExpandedChange(){
        state.expanded = !state.expanded
    }

    fun onViewProduct(product: Product, navigateView: () -> Unit){
        viewModelScope.launch {
            val result = ProductRepository.existProduct(product.id)
            if(result)
                //TODO("Implementar Navegación a vista producto")
                navigateView()
        }
    }

    fun onAddProduct(navigateBack: () -> Unit){
        navigateBack()
    }

    fun onEditProduct(product: Product, navigateBack: () -> Unit){
        viewModelScope.launch {
            val result = ProductRepository.existProduct(product.id)
            if(result)
                navigateBack()
        }

    }

    fun onDeleteProduct(product: Product){
        viewModelScope.launch {
            ProductRepository.deleteProduct(product)
        }
    }

    fun onFilterProduct(string: String){
        if(string == "Sin Tags"){
            val result = list.filter { it.tags.contains(string) }
            if(result.isNotEmpty())
                state = ProductListState.Success(result)
            else
                state = ProductListState.NoData
        }
        else{
            state = ProductListState.Success(list)
        }


    }

    fun onBackProduct(navigateBack: () -> Unit){
        navigateBack()
    }

    fun onAccountView(){
        //TODO("Implementar Navegación a la vista de cuenta cuando este")

    }



}
