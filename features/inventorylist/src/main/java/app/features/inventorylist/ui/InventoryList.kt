package app.features.inventorylist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.composables.TopAppBarTitle
import app.features.inventorylist.R

@Composable
fun InventoryList(modifier: Modifier = Modifier){
    var code = rememberSaveable() { mutableStateOf("") }
    var inventorydescr = rememberSaveable() { mutableStateOf("") }


    /*TopAppBarTitle(title = stringResource(R.string.Titulo)) {
        Box(
            modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()).background(Color.Gray).fillMaxSize()
        ){
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(5) { index ->
                        Card(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                                .fillMaxSize(),
                            shape = RoundedCornerShape(16.dp),
                            onClick = {}
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(app.base.ui.R.drawable.ic_cactus),
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp).size(84.dp).clip(
                                        RoundedCornerShape(corner = CornerSize(16.dp))
                                    )
                                )
                                Column(
                                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Column {
                                        Text(code.value+" $index")
                                    }
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Text(inventorydescr.value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/

}
@Preview
@Composable
fun InventoryListPreview(){
    InventoryList()
}