package app.features.inventorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.BaseResult
import app.base.utils.isValidShortName
import app.domain.ddd.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryCreationViewModel : ViewModel() {
    var state by mutableStateOf(InventoryCreationState())
    private set


    fun onCodeChange(code:String){
        if(code.contains(' '))
            return
        if(code == null || code.isEmpty()){
            state =state.copy(code = code, isCodeError = true, ErrorCodeFormat = "ERROR. Formato no correcto",)
            return
        }
        else{
            state = state.copy(code = code, isCodeError = false, ErrorCodeFormat = "")
        }
    }
    fun onNameChange(name:String){
        if (name.contains(' '))
            return
        if(name == null || name.isEmpty()){
            state = state.copy(name = name, ErrorNameFormat = "ERROR. Campo vacío", isNameError = true)
            return
        }
        else{
            state = state.copy(name = name, ErrorNameFormat = "", isNameError = false)
        }
    }
    fun onDescriptionChange(description:String){
        if(description.contains(' ')){
            return
        }
        if(description == null || description.isEmpty()){
            state = state.copy(description = description, ErrorDescriptionFormat = "ERROR. Campo vacío", isDescriptionError = true)
        }
        else{
            state = state.copy(description = description, isDescriptionError = false, ErrorDescriptionFormat = "")
        }
    }
    fun onShortNameChange(shortname:String){
        if(shortname.contains(' '))
            return
        if(!isValidShortName(shortname) || shortname.length<3){
            state = state.copy(shortName = shortname, ErrorShortNameFormat = "ERROR. Formato mal puesto", isShortNameError = true)
            return
        }
        else{
            state = state.copy(shortName = shortname, isShortNameError = false, ErrorShortNameFormat = "")
        }
    }
    fun onCreationClick(){
        if(isEmptyFields()){
            state = state.copy(isEmpty = "a")
        }
        if(isErrorFields()){
            return
        }
        viewModelScope.launch {
            val response = InventoryRepository.isDuplicate(state.code)
            when(response){
                is BaseResult.Error ->{state = state.copy(isCodeError = state.isCodeError)}
                is BaseResult.Success ->{state = state.copy(Success = true)}
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