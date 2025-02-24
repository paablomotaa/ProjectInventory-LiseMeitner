package app.features.inventorylist.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repositoryDB.InventoryRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryListViewModel @Inject constructor(private val inventoryRepository: InventoryRepositoryDB) : ViewModel() {
    var state by mutableStateOf<InventoryListState>(InventoryListState.Loading)
        private set

    var inventoryid by mutableStateOf<Int?>(null)

    var stateexpanded by mutableStateOf(InventoryDropMenuState())
        private set

    var listinvent: List<Inventory> by mutableStateOf(emptyList())
        private set

    var productDelete by mutableStateOf<Inventory?>(null)

    init {
        getList()
    }

    /**
     * Obtiene la lista de inventarios desde el repositorio y actualiza el estado en consecuencia.
     */
    fun getList() {
        viewModelScope.launch {
            inventoryRepository.getData().collect { inventories ->
                state = InventoryListState.Loading
                if (inventories.isNotEmpty()) {
                    listinvent = inventories
                    state = InventoryListState.Succes(listinvent)
                } else {
                    state = InventoryListState.NoData
                }
            }
        }
    }

    /**
     * Cambia el estado de expansión del menú desplegable.
     *
     * @param expanded
     *
     */
    fun onExpandedChange(expanded: Boolean) {
        stateexpanded = stateexpanded.copy(expanded = expanded)
    }

    /**
     * Maneja la navegación a la vista de detalles de un inventario.
     *
     * @param inventory
     *
     * @param navigateView
     *
     */
    fun onViewInventory(inventory: Inventory, navigateView: (Inventory) -> Unit) {
        inventoryid = inventory.id
        Log.e("", inventory.toString())
        navigateView(inventory)
    }

    /**
     * Maneja la navegación a la pantalla de creación de un nuevo inventario.
     *
     * @param navigateAdd
     *
     */
    fun onAddInventory(navigateAdd: () -> Unit) {
        state = InventoryListState.Loading
        navigateAdd()
    }

    /**
     * Verifica si un inventario existe antes de permitir su edición y maneja la navegación a la pantalla de edición.
     *
     * @param inventory
     *
     * @param navigateEdit
     *
     */
    fun onEditInventory(inventory: Inventory, navigateEdit: () -> Unit) {
        viewModelScope.launch {
            val result = inventoryRepository.getDataById(inventory.id)
            if (result != null) {
                navigateEdit()
            }
        }
    }

    /**
     * Filtra la lista de inventarios según el tipo especificado.
     *
     * @param tipo
     *
     */
    fun onFilterInventory(tipo: String): List<Inventory> {
        return when (tipo) {
            "Semestral", "Anual", "Bianual" -> listinvent.filter { it.type == tipo }
            else -> emptyList()
        }
    }

    /**
     * Maneja la acción de visualización de cuenta (actualmente vacío).
     */
    fun onAccountView() {
        // Implementación futura
    }

    /**
     * Elimina un inventario por su ID y actualiza la lista de inventarios.
     *
     * @param id
     *
     */
    fun onDelete(inventory: Inventory) {
        viewModelScope.launch {
            state = InventoryListState.Loading
            inventoryRepository.deleteInventory(inventory)
            getList()
        }
    }
}