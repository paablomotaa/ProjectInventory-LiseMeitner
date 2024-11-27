package app.base.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.base.ui.R

@Composable
fun BaseImage(modifier: Modifier = Modifier, image: MutableState<ImageBitmap>? = null){
    val imageModifier = modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
        .background(Color.Gray, shape = CircleShape)
        .clip(CircleShape)

    val painter = if (image?.value != null) {
        BitmapPainter(image.value!!)
    } else {
        painterResource(id = R.drawable.ic_cactus)
    }
    Image(
        painter = painter,
        contentDescription = "hola",
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )
}

/*
@Composable
fun rememberSaveableBitmap(): MutableState<Bitmap?> {
    return rememberSaveable(saver = BitmapSaver()) { mutableStateOf(null) }
}*/