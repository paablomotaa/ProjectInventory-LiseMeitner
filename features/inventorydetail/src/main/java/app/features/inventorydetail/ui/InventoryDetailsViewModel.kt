package app.features.inventorydetail.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.ddd.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryDetailsViewModel : ViewModel() {
    var state by mutableStateOf<InventoryDetailsState>(InventoryDetailsState.Loading)
    private set

    fun getInventoryInfo(id: Int?){
            viewModelScope.launch {
                val inventory = InventoryRepository.findInventory(id)
                if (inventory != null) {
                    state = InventoryDetailsState.Success(inventory)
                    Log.e("InventoryDetailsVM", "Exception en success")
                } else {
                    state = InventoryDetailsState.NoData
                    Log.e("InventoryDetailsVM", "Exception en noData: " + id)
                }
            }
    }
}