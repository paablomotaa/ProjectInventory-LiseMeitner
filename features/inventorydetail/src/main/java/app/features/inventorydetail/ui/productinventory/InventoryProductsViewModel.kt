package app.features.inventorydetail.ui.productinventory

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.model.inventoryproducts.InventoryProducts
import app.domain.invoicing.product.Product
import app.domain.invoicing.repositoryDB.InventoryProductsRepositoryDB
import app.domain.invoicing.repositoryDB.ProductRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryProductsViewModel @Inject constructor(
    private val provideProductRepository: ProductRepositoryDB,
    private val provideInventoryProducts: InventoryProductsRepositoryDB
) : ViewModel() {
    var state by mutableStateOf<InventoryProductsState>(InventoryProductsState.Loading)
        private set
    var _list: List<Product> by mutableStateOf(emptyList())
        private set
    private val _selectedProducts = mutableStateListOf<Product>()
    val selectedProducts: List<Product> get() = _selectedProducts

    init {
        getProductList()
    }

    fun getProductList(){
        viewModelScope.launch {
            state = InventoryProductsState.Loading
            provideProductRepository.getAll().collect{ products ->
                if(products.isNotEmpty()){

                    _list = products

                    state = InventoryProductsState.Succes(_list)
                }
                else
                    state = InventoryProductsState.NoData

            }
        }
    }


    fun toggleProductSelection(product: Product) {
        if (_selectedProducts.contains(product)) {
            _selectedProducts.remove(product)
        } else {
            _selectedProducts.add(product)
        }
        Log.d("SI","Size de la lista: " + selectedProducts.size)
    }

    fun onCheckList(id:Int,goBack:() -> Unit){
        viewModelScope.launch {
            val response = provideInventoryProducts.getDataById(id)
            if(response != null){
                val result = response.copy(products = _selectedProducts)

                provideInventoryProducts.update(result)
            }
            goBack()
        }
    }
}