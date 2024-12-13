package app.features.inventorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.base.utils.BaseResult
import app.base.utils.isValidShortName
import app.domain.ddd.repository.inventory.InventoryRepository
import kotlinx.coroutines.launch

class InventoryCreationViewModel : ViewModel() {
    var state by mutableStateOf(InventoryCreationState())
    private set


    fun onCodeChange(code:String){
        if(code.contains(' '))
            return
        if(code == null){
            state =state.copy(code = code, isCodeError = true, ErrorCodeFormat = "ERROR. Formato no correcto",)
            return
        }
        else{
            state = state.copy(code = code, isCodeError = false)
        }
    }
    fun onNameChange(name:String){
        if (name.contains(' '))
            return
        if(name == null){
            state = state.copy(name = name, ErrorCodeFormat = "ERROR. Campo vacío", isNameError = true)
            return
        }
        else{
            state = state.copy(name = name, ErrorCodeFormat = "ERROR. Campo vacío", isNameError = false)
        }
    }
    fun onDescriptionChange(description:String){
        if(description.contains(' ')){
            return
        }
        if(description == null){
            state = state.copy(description = description, ErrorCodeFormat = "ERROR. Campo vacío", isDescriptionError = true)
            return
        }
        else{
            state = state.copy(name = description, isDescriptionError = false)
        }
    }
    fun onShortNameChange(shortname:String){
        if(shortname.contains(' '))
            return
        if(shortname == null || !isValidShortName(shortname) || shortname.length<3){
            state = state.copy(description = shortname, ErrorCodeFormat = "ERROR. Campo vacío", isShortNameError = true)
            return
        }
        else{
            state = state.copy(name = shortname, isShortNameError = false)
        }
    }
    fun onCreationClick(){
        if(isEmptyFields()){
            state = state.copy(isEmpty = "Campos vacios")
        }
        if(isErrorFields()){
            return
        }
        viewModelScope.launch {
            val response =InventoryRepository.isDuplicate(state.code)
            when(response){
                is BaseResult.Error ->{state = state.copy(isCodeError = state.isCodeError)}
                is BaseResult.Success ->{state = state.copy(Success = true)}
            }
        }
    }

    private fun isEmptyFields(): Boolean{
        return (state.code.isEmpty() || state.name.isEmpty() || state.shortName.isEmpty() || state.description.isEmpty())
    }
    private fun isErrorFields():Boolean{
        return (state.isCodeError || state.isNameError || state.isShortNameError || state.isDescriptionError || state.isErrorCreation)
    }
}