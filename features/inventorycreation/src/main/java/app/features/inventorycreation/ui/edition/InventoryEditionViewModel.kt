package app.features.inventorycreation.ui.edition

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.isValidShortName
import app.domain.ddd.repository.InventoryRepository
import app.domain.invoicing.inventory.Inventory
import app.features.inventorycreation.ui.creation.InventoryCreationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryEditionViewModel @Inject constructor(private val provideInventoryRepository: InventoryRepository) : ViewModel() {
    var state by mutableStateOf(InventoryEditionState())
    private set
    var stateView by mutableStateOf<InventoryEditionStateView>(InventoryEditionStateView.Loading)
    private set


    fun getInventory(id:Int){
        viewModelScope.launch {
            val inventory = provideInventoryRepository.findInventory(id)
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
    fun onDescriptionChange(description:String){
        if (description.isEmpty())
            state = state.copy(description = description, isDescriptionError = true, ErrorDescriptionFormat = "ERROR. El formato de la descripcion esta mal")
        else
            state = state.copy(description = description, isDescriptionError = false, ErrorDescriptionFormat = "")
    }
    fun onShortNameChange(shortname:String){
        if(!isValidShortName(shortname) || shortname.length<3){
            state = state.copy(shortName = shortname, ErrorShortNameFormat = "ERROR. Formato mal puesto", isShortNameError = true)
            return
        }
        else{
            state = state.copy(shortName = shortname, isShortNameError = false, ErrorShortNameFormat = "")
        }
    }
    fun onExpandeChange(expanded:Boolean){
        state = state.copy(expanded = expanded)
    }
    fun onValueChange(tipo:String){
        state = state.copy(type = tipo)
    }

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
            val inventoryold = provideInventoryRepository.existInventory(state.id)
            if(inventoryold){
                val response = provideInventoryRepository.edit(
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
    private fun isErrorFields():Boolean{
        return (state.isCodeError || state.isNameError || state.isShortNameError || state.isDescriptionError || state.isErrorCreation)
    }
}