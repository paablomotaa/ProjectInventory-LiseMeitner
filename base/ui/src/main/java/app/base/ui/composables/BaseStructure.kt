package app.base.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import app.base.ui.Separations

@Composable
fun BaseStructureCompletePadding(
    modifier: Modifier,
    separations: Dp,
    scrolleable: Boolean = false,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Separations.Medium,
                Alignment.CenterVertically
            ),
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
fun BaseStructureCompletePaddingNoCenter(
    modifier: Modifier,
    separations: Dp,
    scrolleable: Boolean = false,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Separations.Medium,
            ),
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
fun BaseStructureCompletePaddingUpSide(
    modifier: Modifier,
    separations: Dp,
    scrolleable: Boolean = false,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Separations.Medium,
                Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(separations, separations, separations, Separations.Zero)
                .then(if (scrolleable) Modifier.verticalScroll(state = scrollState) else Modifier),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            content()
        }
    }
}

@Composable
fun BaseStructureColumnPaddingUpSide(
    modifier: Modifier,
    separations: Dp,
    scrolleable: Boolean = false,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(
            Separations.Medium,
            Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(separations, separations, separations, Separations.Zero)
            .then(if (scrolleable) Modifier.verticalScroll(state = scrollState) else Modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        content()
    }

}

@Composable
fun BaseStructureCollumnPadding(
    modifier: Modifier,
    separations: Dp,
    scrolleable: Boolean = false,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Separations.Medium,
                Alignment.CenterVertically
            ),
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
fun BaseRow(separetion: Dp, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(separetion)
    ) {
        content()
    }
}