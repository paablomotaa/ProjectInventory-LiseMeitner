package app.base.ui.composables

import androidx.compose.ui.graphics.vector.ImageVector

data class BaseTopAppBarState(
    val title: String,
    val iconUpAction: ImageVector,
    val actions: List<Actions>,
    val upAction: () -> Unit
)

data class Actions(
    val title: String,
    val contentDescription: String,
    val icon: ImageVector?,
    val onClick: () -> Unit,
    val isVisible: Boolean
)