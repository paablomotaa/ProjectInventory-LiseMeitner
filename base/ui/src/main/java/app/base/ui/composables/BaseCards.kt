package app.base.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.base.ui.Separations

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardRow(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Card(
        modifier = modifier
            .padding(Separations.Small)
            .height(Separations.SuperLarge)
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
            )
    ) {

        Row(
            modifier = modifier
                .padding(Separations.Medium)
                .align(alignment = Alignment.Start)
        ) {
            content()
        }
    }
}