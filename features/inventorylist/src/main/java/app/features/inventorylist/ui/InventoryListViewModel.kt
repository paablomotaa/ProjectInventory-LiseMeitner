package app.features.inventorylist.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.BaseResult
import app.domain.ddd.repository.InventoryRepository
import app.domain.invoicing.inventory.Inventory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryListViewModel @Inject constructor(private val provideInventoryRepository: InventoryRepository) : ViewModel() {
    var state by mutableStateOf<InventoryListState>(InventoryListState.Loading)
    private set

    var inventoryid by mutableStateOf<Int?>(null)

    var stateexpanded by mutableStateOf(InventoryDropMenuState())
    private set

    var listinvent: List<Inventory> by mutableStateOf(emptyList())
    private set

    var productDelete by mutableStateOf<Inventory?>(null)


    init{
        getList()
    }

    fun getList(){
        viewModelScope.launch {
            provideInventoryRepository.getData().collect{ inventories ->
                state = InventoryListState.Loading
                if(inventories.isNotEmpty()){
                    listinvent = inventories
                    state = InventoryListState.Succes(listinvent)
                }
                else{
                    state = InventoryListState.NoData
                }
            }
        }
    }
    fun onExpandedChange(expanded:Boolean){
        stateexpanded = stateexpanded.copy(expanded = expanded)
    }
    fun onViewInventory(inventory: Inventory,navigateView:(Inventory)->Unit){
        inventoryid = inventory.id
        Log.e("",inventory.toString())
        navigateView(inventory)
    }

    fun onAddInventory(navigateAdd: () -> Unit){
        state = InventoryListState.Loading
        navigateAdd()
    }
    fun onEditInventory(inventory: Inventory,navigateEdit:() -> Unit){
        viewModelScope.launch{
            val result = InventoryRepository.existInventory(inventory.id)
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
    fun onDelete(id:Int){
        viewModelScope.launch {
            state = InventoryListState.Loading
            provideInventoryRepository.delete(id)
            getList()
        }
    }
}