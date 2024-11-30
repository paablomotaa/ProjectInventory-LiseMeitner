package app.base.ui.composables

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

@Composable
fun CardRow(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Card(
        modifier = modifier
            .padding(Separations.Small)
            .height(Separations.SuperLarge)
            .fillMaxWidth(), onClick = onClick
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