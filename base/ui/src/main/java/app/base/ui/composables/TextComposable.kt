package app.base.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import app.base.ui.Dimensions

@Composable
fun TitleText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = text, fontWeight = FontWeight.Bold, fontSize = Dimensions.Medium,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun MediumTitleText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = text, fontWeight = FontWeight.Bold, fontSize = Dimensions.Small,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun ErrorTextInputField(text: String) {
    Text(text = text,color = Color.Red)
}