package app.base.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun BaseStructureCenter(modifier: Modifier, separations: Dp, scrolleable: Boolean = false, content: @Composable () -> Unit) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Separations.Medium, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(separations)
                .then(if (scrolleable) Modifier.verticalScroll(state = scrollState) else Modifier),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            content()
        }
    }
}

@Composable
fun BaseStructureTop(modifier: Modifier, separations: Dp, scrolleable: Boolean = false, content: @Composable () -> Unit) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Separations.Medium, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(separations)
                .then(if (scrolleable) Modifier.verticalScroll(state = scrollState) else Modifier),
            horizontalAlignment = Alignment.Start
        )
        {
            content()
        }
    }
}