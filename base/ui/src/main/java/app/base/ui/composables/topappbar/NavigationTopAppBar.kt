package app.base.ui.composables.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationTopAppBar(val func:() -> Unit, val icon: ImageVector, val label: String) {
   class OptDrawer(onClick:() ->Unit,label:String): NavigationTopAppBar(onClick,Icons.Default.Menu,label)
    class BackPage(onClick: () -> Unit,label:String): NavigationTopAppBar(onClick,Icons.Default.ArrowBack,label)
}