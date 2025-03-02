package com.example.login.ui.feature.Account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.components.NoDataScreen
import app.base.ui.composables.Actions
import app.base.ui.composables.BaseTopAppBar
import app.base.ui.composables.BaseTopAppBarState
import app.features.accountsettings.R
import com.example.login.base.icon_composable.ascendSort
import com.example.login.base.icon_composable.descendSort
import com.example.login.data.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AccountsListScreen(viewModel: AccountListViewModel,
                       openDrawer: () -> Unit,
                       goToCreation: () -> Unit,
                       goToDetail: (Account) -> Unit,
                       modifier: Modifier = Modifier) {

    //1. Control de la logica y el estado
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    //2. Crear las variables de los eventos
    val events = AccountsListEvents(
        openDrawer = openDrawer,
        goToCreation = goToCreation,
        goToDetail = goToDetail,
        //Este evento es interno en la lista
        goToDelete = { account -> viewModel.delete(account) },
        sortBy = { viewModel.sortBy() }
    )
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            BaseTopAppBar(appBarState(events, viewModel.stateView))
        },
        floatingActionButton = {
            FloatingActionButton(onClick = events.goToCreation){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    )
    { innerpadding ->
        when (viewModel.state) {
            is AccountListState.NoData -> {
                NoDataScreen(modifier)
            }

            is AccountListState.Success -> {
                AccountsListContent(

                    accounts = viewModel.list,
                    modifier = modifier.padding(innerpadding),
                    events = events,
                    scope = scope,
                    snackbarHostState = snackbarHostState
                )
                //(viewModel.state as AccountListState.Success.dataset)
            }

            is AccountListState.Loading -> {
                LoadingUi()
            }
        }
    }
}


/**
 * Clase que engloba los eventos
 *
 * @property openDrawer
 * @property goToCreation
 * @property goToDetail
 * @property goToDelete
 * @constructor Create empty Accounts list events
 */
data class AccountsListEvents(
    val openDrawer: () -> Unit,
    val goToCreation: () -> Unit,
    val goToDetail: (Account) -> Unit,
    val goToDelete: (Account) -> Unit,
    val sortBy: () -> Unit
)

@Composable
fun AccountsListContent(modifier: Modifier, accounts: List<Account>, events: AccountsListEvents, scope: CoroutineScope, snackbarHostState: SnackbarHostState){
    var accountToDelete by remember{mutableStateOf<Account?>(null)}
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        modifier = modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        //Account debe  definir un metodo equals o hashCode
        items(accounts) { account ->
            AccountItem(
                account = account,
                goToDelete = { selectedAccount ->
                    accountToDelete = selectedAccount
                },
                goToDetail = events.goToDetail,
                scope = scope,
                snackbarHostState = snackbarHostState)
        }
    }
    if(accountToDelete != null){
        DeleteAccountDialog(
            account = accountToDelete!!,
            onConfirm = {
                events.goToDelete(accountToDelete!!)
                accountToDelete = null //Aqui ya se ha eliminado
            },
            onDismiss = {accountToDelete = null } //Anulo la operación onLongClick
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountItem( modifier: Modifier = Modifier,account: Account, goToDelete: (Account)-> Unit, goToDetail: (Account)-> Unit,scope: CoroutineScope, snackbarHostState: SnackbarHostState){
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 6.dp, // Elevación para sombra
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .combinedClickable(
                onClick = {
                    scope.launch{
                        snackbarHostState.showSnackbar(
                            message = "Cuenta ${account.name}, ${account.surname}, ${account.email}, ${account.userName}, ${account.dateOfBirth}",
                            actionLabel = "Aceptar",
                            withDismissAction = true
                        )
                    }
                },
                onLongClick = { goToDelete(account) }

            )// Espaciado externo
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth() // Se ajusta al ancho del contenedor
                .padding(16.dp) // Espaciado interno
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape) // Imagen circular
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)) // Fondo suave
            ) {
                Image(
                    painter = painterResource(R.drawable.account_icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp) // Espaciado interno para separar del borde
                )
            }
            Text(
                text = account.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

/**
 * Función interna que construye el estado de la app bar
 *
 * @param events
 */
@Composable
fun appBarState(events: AccountsListEvents, stateView: Boolean): BaseTopAppBarState {
    return BaseTopAppBarState(
        title = stringResource(R.string.title_accounts_list_screen),
        iconUpAction = Icons.Default.Menu,
        upAction = {events.openDrawer},
        actions = listOf(
            Actions(
                title = stringResource(R.string.sort),
                icon = if(stateView) ascendSort() else descendSort(),
                contentDescription = stringResource(R.string.icon_sort_content_description),
                onClick = { events.sortBy() },
                isVisible = true
            ),
            Actions(
                title = stringResource(R.string.name_action_setting),
                icon = null,
                contentDescription = stringResource(R.string.icon_settings_content_description),
                onClick = {},
                isVisible = false
            ),
            Actions(
                title = stringResource(R.string.name_icon_logout),
                icon = null,
                contentDescription = stringResource(R.string.icon_settings_content_description),
                onClick = {},
                isVisible = false
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    //AccountScreen(viewModel = AccountListViewModel())
}