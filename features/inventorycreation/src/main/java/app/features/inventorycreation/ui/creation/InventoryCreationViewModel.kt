package app.features.inventorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.BaseResult
import app.base.utils.isValidShortName
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repositoryDB.InventoryRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryCreationViewModel @Inject constructor(private val provideInventoryRepository: InventoryRepositoryDB): ViewModel() {
    var state by mutableStateOf(InventoryCreationState())
        private set
    var code = 1

    /**
     * Maneja los cambios en el código del inventario y valida su formato.
     *
     * @param code
     *
     */
    fun onCodeChange(code:String){
        if(code.contains(' '))
            return
        if(code.isEmpty()){
            state = state.copy(code = code, isCodeError = true, ErrorCodeFormat = "ERROR. Formato no correcto")
            return
        }
        else{
            state = state.copy(code = code, isCodeError = false, ErrorCodeFormat = "")
        }
    }

    /**
     * Maneja los cambios en el nombre del inventario y verifica si está vacío.
     *
     * @param name
     *
     */
    fun onNameChange(name:String){
        if(name.isEmpty()){
            state = state.copy(name = name, ErrorNameFormat = "ERROR. Campo vacío", isNameError = true)
            return
        }
        else{
            state = state.copy(name = name, ErrorNameFormat = "", isNameError = false)
        }
    }

    /**
     * Maneja los cambios en la descripción del inventario y verifica si está vacía.
     *
     * @param description
     *
     */
    fun onDescriptionChange(description:String){
        if(description.isEmpty()){
            state = state.copy(description = description, ErrorDescriptionFormat = "ERROR. Campo vacío", isDescriptionError = true)
        }
        else{
            state = state.copy(description = description, isDescriptionError = false, ErrorDescriptionFormat = "")
        }
    }

    /**
     * Maneja los cambios en el nombre corto del inventario y valida su formato.
     *
     * @param shortname
     *
     */
    fun onShortNameChange(shortname:String){
        if(shortname.contains(' '))
            return
        if(!isValidShortName(shortname) || shortname.length < 3){
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
     * @param expanded
     *
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
     * Maneja la acción de creación del inventario, validando que no haya errores antes de guardarlo.
     *
     * @param onBack
     *
     */
    fun onCreationClick(onBack:() -> Unit){
        if(isEmptyFields()){
            state = state.copy(isEmpty = "a")
        }
        if(isErrorFields()){
            return
        }

        viewModelScope.launch {
            code += 1
            state = state.copy(id = code)
            val inventory = Inventory(state.id, state.code, state.name, state.shortName, state.description, state.type, state.dateActive, state.dateProgress, state.dateHistory)

            if(provideInventoryRepository.getDataById(inventory.id) != null){
                state = state.copy(isCodeError = true, ErrorCodeFormat = "Inventario duplicado, por favor elige otro código")
                return@launch
            }
            else{
                val response = provideInventoryRepository.insertInventory(inventory)
                when(response){
                    is BaseResult.Error -> {
                        state = state.copy(isCodeError = state.isCodeError)
                    }
                    is BaseResult.Success -> {
                        state = state.copy(Success = true, code = "", name = "", description = "", shortName = "")
                        onBack()
                    }
                }
            }
        }
    }

    /**
     * Verifica si hay campos vacíos en el estado del inventario y actualiza los errores correspondientes.
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
