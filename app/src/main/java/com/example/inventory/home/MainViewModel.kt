package com.example.inventory.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val session: Session): ViewModel() {
    var state by mutableStateOf(MainState(activeAccount = false))
        private set
    var nameAccount by mutableStateOf("")
    //Comprobar en ele DataStore si el usuario ha iniciado sesion

    fun isActiveAccount(){
        viewModelScope.launch {

            //Se lee el valor del fichero DataStore
            session.isUserLoggedIn().collect{
                //Se ejecuta el código que se encuentra dentro del bloque
                //snapshotFlow de HomeScreen
                state = state.copy(activeAccount = it)
            }
        }
    }

    fun getName()
    {
        viewModelScope.launch {
            session.getUser().collect{
                nameAccount = it
            }
        }
    }
}