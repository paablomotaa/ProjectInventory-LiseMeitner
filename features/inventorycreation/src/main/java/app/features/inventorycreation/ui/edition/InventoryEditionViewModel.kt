package app.features.inventorycreation.ui.edition

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.isValidShortName
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repositoryDB.InventoryRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryEditionViewModel @Inject constructor(private val provideInventoryRepository: InventoryRepositoryDB) : ViewModel() {
    var state by mutableStateOf(InventoryEditionState())
        private set
    var stateView by mutableStateOf<InventoryEditionStateView>(InventoryEditionStateView.Loading)
        private set

    /**
     * Obtiene un inventario de la base de datos a partir de su ID y actualiza el estado con sus datos.
     *
     * @param id
     */
    fun getInventory(id:Int){
        viewModelScope.launch {
            val inventory = provideInventoryRepository.getDataById(id)
            if (inventory != null) {
                if (state.id == 0) {
                    state = state.copy(
                        id = inventory.id,
                        code = inventory.code,
                        name = inventory.name,
                        shortName = inventory.shortName,
                        description = inventory.description,
                        type = inventory.type
                    )
                }
            }
            stateView = InventoryEditionStateView.Success
        }
    }

    /**
     * Maneja los cambios en el código del inventario y valida si está vacío.
     *
     * @param code
     */
    fun onCodeChange(code:String){
        Log.d("InventoryEdition", "onCodeChange ejecutado con valor: $code")
        if(code.isEmpty())
            state = state.copy(
                code = code,
                isCodeError = true,
                ErrorCodeFormat = "ERROR. El formato del codigo esta mal"
            )
        else
            state = state.copy(code = code, isCodeError = false, ErrorCodeFormat = "")
    }

    /**
     * Maneja los cambios en el nombre del inventario y valida si está vacío.
     *
     * @param name
     */
    fun onNameChange(name:String){
        if(name.isEmpty())
            state = state.copy(
                name = name,
                isNameError = true,
                ErrorNameFormat = "ERROR. El formato del nombre esta mal"
            )
        else
            state = state.copy(
                name = name,
                isNameError = false,
                ErrorNameFormat = ""
            )
    }

    /**
     * Maneja los cambios en la descripción del inventario y valida si está vacía.
     *
     * @param description
     */
    fun onDescriptionChange(description:String){
        if (description.isEmpty())
            state = state.copy(description = description, isDescriptionError = true, ErrorDescriptionFormat = "ERROR. El formato de la descripcion esta mal")
        else
            state = state.copy(description = description, isDescriptionError = false, ErrorDescriptionFormat = "")
    }

    /**
     * Maneja los cambios en el nombre corto del inventario y valida su formato y longitud.
     *
     *
     * @param shortname
     */
    fun onShortNameChange(shortname:String){
        if(!isValidShortName(shortname) || shortname.length<3){
            state = state.copy(shortName = shortname, ErrorShortNameFormat = "ERROR. Formato mal puesto", isShortNameError = true)
            return
        }
        else{
            state = state.copy(shortName = shortname, isShortNameError = false, ErrorShortNameFormat = "")
        }
    }

    /**
     * Cambia el estado de expansión del inventario en la interfaz.
     *
     *
     * @param expanded
     */
    fun onExpandeChange(expanded:Boolean){
        state = state.copy(expanded = expanded)
    }

    /**
     * Maneja los cambios en el tipo de inventario.
     *
     * @param tipo
     *
     */
    fun onValueChange(tipo:String){
        state = state.copy(type = tipo)
    }

    /**
     * Maneja la acción de edición del inventario, validando campos vacíos o con errores antes de guardar los cambios.
     *
     * @param onAccept
     *
     */
    fun onEditClick(onAccept:()-> Unit){
        Log.d("Inventory editado", "he llegado a aqui")
        if(isEmptyFields()){
            state = state.copy(isEmpty = "ERROR. Algun campo esta vacío")
            Log.d("Inventory editado", "paso por empty")
        }
        if(isErrorFields()){
            Log.d("Inventory editado", "paso por error")
            return
        }

        viewModelScope.launch {
            val inventoryold = provideInventoryRepository.getDataById(state.id)
            if(inventoryold != null){
                val response = provideInventoryRepository.update(
                    Inventory(
                        id = state.id,
                        code = state.code,
                        name = state.name,
                        shortName = state.shortName,
                        description = state.description,
                        type = state.type,
                        dateHistory = state.dateHistory,
                        dateActive = state.dateActive
                    )
                )
                Log.d("Inventory editado", "Inventario editado correctamente" + response)
                stateView = InventoryEditionStateView.Success
                onAccept()
            }
        }
    }

    /**
     * Verifica si hay campos vacíos en el estado del inventario y actualiza los errores correspondientes.
     *
     *
     */
    private fun isEmptyFields(): Boolean {
        var hasEmptyFields = false

        if (state.code.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isCodeError = true, ErrorCodeFormat = "El código no puede estar vacío")
        }

        if (state.name.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isNameError = true, ErrorNameFormat = "El nombre no puede estar vacío")
        }

        if (state.description.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isDescriptionError = true, ErrorDescriptionFormat = "La descripción no puede estar vacía")
        }

        if (state.shortName.isBlank()) {
            hasEmptyFields = true
            state = state.copy(isShortNameError = true, ErrorShortNameFormat = "El nombre corto no puede estar vacío")
        }

        return hasEmptyFields
    }

    /**
     * Verifica si hay errores en los campos del estado del inventario.
     */
    private fun isErrorFields():Boolean{
        return (state.isCodeError || state.isNameError || state.isShortNameError || state.isDescriptionError || state.isErrorCreation)
    }
}