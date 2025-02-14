package app.base.ui.composables.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationTopAppBar(
    val func:() -> Unit,
    val icon: ImageVector,
    val label: String,
    val actions: List<Action> = emptyList(),
    val floating: Action.ComplexAction? = null) {
   class OptDrawer(onClick:() ->Unit,label:String, actions: List<Action> = emptyList(), floating: Action.ComplexAction? = null):
       NavigationTopAppBar(onClick,Icons.Default.Menu,label,actions, floating)
   class BackPage(onClick: () -> Unit,label:String, actions: List<Action> = emptyList(), floating: Action.ComplexAction? = null):
        NavigationTopAppBar(onClick, Icons.AutoMirrored.Filled.ArrowBack,label,actions, floating)
}

sealed class Action{
    data class SimpleAction(
        val icon: ImageVector,
        val label: String,
        val onClick: () -> Unit
    ): Action()

    data class DropDown<T>(
        val expandedValue: Boolean = false,
        val onExpandeValueChange: (Boolean) -> Unit,
        val menuItemData: List<T>,
        val function: (T) -> Unit
    ): Action()

    data class ComplexAction(
        val icon: ImageVector,
        val label: String,
        val onClick: (() -> Unit) -> Unit,
        val onGo:() -> Unit
    )
}
