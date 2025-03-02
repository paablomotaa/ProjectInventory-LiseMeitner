package app.features.accountsettings.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repositoryDB.AccountRepositoryDB
import com.example.login.data.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountListViewModel
@Inject constructor(private val accountRepository: AccountRepositoryDB): ViewModel() {

    var state by mutableStateOf<AccountListState>(AccountListState.Loading)
        private set
    /*
    var list by mutableStateOf<List<Account>>(emptyList<Account>())
        private set
    */
    var stateView by mutableStateOf(true)

    var list: List<Account> by mutableStateOf(emptyList())
        private set

    var dataset by mutableStateOf<List<Account>>(emptyList())

    fun getList() {
        //Se inicia la corrutina
        viewModelScope.launch {
            state = AccountListState.Loading
            accountRepository.getData().collect { accounts ->
                if(accounts.isNotEmpty()){
                    //1. Si hay datos
                    list = accounts
                    Log.d("AccountListViewModel", "Success")
                    state = AccountListState.Success(accounts)
                }
                else{

                    Log.d("AccountListViewModel", "NoData")
                    //2. Si no hay datos
                    state = AccountListState.NoData
                }
            }
        }
    }

    fun delete(account: Account) {
        viewModelScope.launch {
            state = AccountListState.Loading
            if(accountRepository.delete(account)){
                getList()
            }
        }
    }

    fun sortBy(){
        if(state is AccountListState.Success){
            if(stateView){
                list = list.sortedBy { it.name }
            }
            else{
                list = list.sortedByDescending { it.name }
            }
            stateView = !stateView
        }
    }
}