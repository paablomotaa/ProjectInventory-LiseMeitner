package app.features.inventorydetail.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.product.Product
import app.domain.invoicing.repositoryDB.InventoryProductsRepositoryDB
import app.domain.invoicing.repositoryDB.InventoryRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryDetailsViewModel @Inject constructor(
    private val provideInventoryRepository: InventoryRepositoryDB,
    private val provideInventoryProductsRepository: InventoryProductsRepositoryDB
) : ViewModel() {
    var state by mutableStateOf<InventoryDetailsState>(InventoryDetailsState.Loading)
        private set
    var listproducts by mutableStateOf<List<Product>>(emptyList())
    private set

    /**
     * Obtiene la información de un inventario a partir de su ID y actualiza el estado en consecuencia.
     *
     * @param id
     *
     */
    fun getInventoryInfo(id: Int){
        viewModelScope.launch {
            val inventory = provideInventoryRepository.getDataById(id)
            val products = provideInventoryProductsRepository.getDataById(id)
            if (inventory != null) {
                state = InventoryDetailsState.Success(inventory)
                listproducts = products.products
            } else {
                state = InventoryDetailsState.NoData
            }
        }
    }

    /**
     * Verifica si el inventario existe antes de permitir la navegación a la pantalla de edición.
     *
     * @param id
     * @param goEdit
     */
    fun onGoEdit(id:Int, goEdit:() -> Unit){
        viewModelScope.launch {
            val res = provideInventoryRepository.getDataById(id)
            if(res !=null){
                goEdit()
            }
        }
    }

    fun onGoToAdd(id:Int,goToAdd:() -> Unit){
        viewModelScope.launch {
            val res = provideInventoryProductsRepository.getDataById(id)
            if(res !=null){
                goToAdd()
            }
        }
    }
}