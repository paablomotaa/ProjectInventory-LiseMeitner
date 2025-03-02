package app.base.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import app.base.ui.composables.topappbar.Action
import app.base.ui.composables.topappbar.NavigationTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> TopAppBarTitle(navigation: NavigationTopAppBar, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    TitleText(navigation.label)
                },
                navigationIcon = {
                    IconButton(onClick = { navigation.func() }) {
                        Icon(
                            imageVector = navigation.icon,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    if (navigation.actions.isNotEmpty()) {
                        navigation.actions.forEach { action ->
                            when (action) {
                                is Action.SimpleAction -> {
                                    IconButton(onClick = { action.onClick() }) {
                                        Icon(
                                            imageVector = action.icon,
                                            contentDescription = action.label
                                        )
                                    }
                                }
                                is Action.DropDown<*> -> {
                                    IconDropDownMenuAnyType(
                                        expandeValue = action.expandedValue,
                                        onExpandeValueChange = action.onExpandeValueChange,
                                        menuItemData = action.menuItemData as List<T>,
                                        function = { item -> (action.function as (T) -> Unit)(item) } // Cast explícito
                                    )
                                }
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (navigation.floating != null) {
                FloatingActionButton(onClick = { navigation.floating.onClick(navigation.floating.onGo) }) {
                    Icon(navigation.floating.icon, contentDescription = navigation.floating.label)
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(appBarState: BaseTopAppBarState) {
    TopAppBar(title = { Text(text = appBarState.title) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
        actions = {
            appBarState.actions.forEach{
                if(it.isVisible){
                    IconButton(onClick = { it.onClick() }) {
                        it.icon?.let { it1 ->
                            Icon(
                                imageVector = it1,
                                contentDescription = it.contentDescription
                            )
                        }
                    }
                }
            }
            var expandeValue by remember { mutableStateOf(false) }

            IconButton(
                onClick = {expandeValue = true}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Content"
                )
            }
            DropdownMenu(
                expanded = expandeValue,
                onDismissRequest = {expandeValue = false}
            ) {
                appBarState.actions.forEach {
                    if(!it.isVisible){
                        DropdownMenuItem(
                            {Text(it.title)},
                            onClick = it.onClick
                        )
                    }
                }
            }

        }
    )
}