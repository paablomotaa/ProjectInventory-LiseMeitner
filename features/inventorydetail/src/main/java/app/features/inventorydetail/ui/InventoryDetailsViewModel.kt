package app.features.inventorydetail.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repositoryDB.InventoryRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryDetailsViewModel @Inject constructor(private val provideInventoryRepository: InventoryRepositoryDB) : ViewModel() {
    var state by mutableStateOf<InventoryDetailsState>(InventoryDetailsState.Loading)
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
            if (inventory != null) {
                state = InventoryDetailsState.Success(inventory)
                Log.e("InventoryDetailsVM", "Exception en success")
            } else {
                state = InventoryDetailsState.NoData
                Log.e("InventoryDetailsVM", "Exception en noData: " + id)
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
}