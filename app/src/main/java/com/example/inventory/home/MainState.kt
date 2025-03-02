package com.example.inventory.home

import app.features.accountsignin.ui.SignUpGraph
import com.example.inventory.navigation.AccountGraph

data class MainState(val activeAccount: Boolean){
    fun starNavDestination() = if(activeAccount)
        AccountGraph.ROUTE
    else
        SignUpGraph.ROUTE
}