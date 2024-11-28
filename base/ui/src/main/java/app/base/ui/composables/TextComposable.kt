package app.base.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text:String){
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 30.sp)
}
@Composable
fun MediumTitleText(text:String){
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 15.sp)
}
@Composable
fun ErrorTextInputField(text:String){
    Text(text = text)
}