package app.features.inventorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.ddd.repository.InventoryRepository
import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class InventoryListViewModel : ViewModel() {
    var state by mutableStateOf<InventoryListState>(InventoryListState.Loading)
    private set

    var listinvent: List<Inventory> by mutableStateOf(emptyList())
    private set
    var listtypes:List<String> by mutableStateOf(listOf("Anual","Bianual"))


    suspend fun getList(){
        state = InventoryListState.Loading
        InventoryRepository.getData().collect{ inventories ->
            if(inventories.isEmpty()){
                listinvent = inventories
                state = InventoryListState.Succes(listinvent)
                listtypes = listinvent.map { it.type } + ("No tipos")
            }
            else
                state = InventoryListState.NoData
        }
    }
    fun onExpandedChange(expanded:Boolean){
        state.expanded = expanded
    }
    fun onViewInventory(navigateView:()->Unit){
        navigateView()
    }
    fun onAddInventory(navigateAdd: () -> Unit){
        navigateAdd()
    }
    fun onEditInventory(inventory: Inventory,navigateEdit:() -> Unit){
        viewModelScope.launch{
            val result = InventoryRepository.existInventory(inventory)
            if(result){
                navigateEdit()
            }
        }
    }
    fun onFilterInventory(tipo:String):List<Inventory>{
        return when (tipo){
            "Semestral","Anual","Bianual" -> listinvent.filter { it.type == tipo }
            else -> emptyList()
        }
    }
    fun onAccountView(){

    }
}