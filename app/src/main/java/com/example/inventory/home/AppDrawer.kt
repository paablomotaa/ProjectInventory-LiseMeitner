import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import app.base.ui.Separations
import app.base.ui.composables.SmallSpace
import app.domain.navigation.CategoryGraph
import com.example.inventory.R
import com.example.inventory.home.NavigationDrawerItemSealed
import com.example.inventory.navigation.InventoryGraph
import com.example.inventory.navigation.ProductGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    navBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(modifier = modifier.width(250.dp)) {
                Column{
                    SmallSpace()
                    Text(
                        text = stringResource(R.string.principal_menu),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(Separations.Medium)
                    )
                    NavigationDrawerItemSealed.list().forEach {
                        NavigationDrawerItem(
                            selected = it.route == currentDestination?.route,
                            icon = { Icon(it.icon, contentDescription = null) },
                            label = { Text(stringResource(it.label)) },
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                                navController.navigate(it.route){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}