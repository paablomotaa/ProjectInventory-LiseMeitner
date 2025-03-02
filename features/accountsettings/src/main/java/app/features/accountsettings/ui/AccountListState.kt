package app.features.accountsettings.ui

import com.example.login.data.model.Account

sealed class AccountListState{
    data object NoData: AccountListState()
    data object Loading: AccountListState()
    //data object Success(val dataList: List<Account>): AccountListState()
    data class Success(var dataList: List<Account>): AccountListState()
}