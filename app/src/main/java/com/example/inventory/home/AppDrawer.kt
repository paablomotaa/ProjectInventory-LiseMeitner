import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.base.ui.Separations
import app.base.ui.composables.SmallSpace
import app.domain.navigation.CategoryGraph
import com.example.inventory.R
import com.example.inventory.navigation.InventoryGraph
import com.example.inventory.navigation.ProductGraph

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(modifier = modifier.width(250.dp)) {
                SmallSpace()
                Text(
                    text = stringResource(R.string.principal_menu),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(Separations.Medium)
                )
                val currentRoute = navController.currentDestination?.route
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.inventory)) },
                    selected = currentRoute == InventoryGraph.ROUTE,
                    onClick = {
                        navController.navigate(InventoryGraph.ROUTE) {
                            launchSingleTop = true
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.category)) },
                    selected = currentRoute == CategoryGraph.ROUTE,
                    onClick = {
                        navController.navigate(CategoryGraph.ROUTE) {
                            launchSingleTop = true
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(R.string.product)) },
                    selected = currentRoute == ProductGraph.ROUTE,
                    onClick = {
                        navController.navigate(ProductGraph.ROUTE) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        },
        drawerState = drawerState
    ) {
        content()
    }
}