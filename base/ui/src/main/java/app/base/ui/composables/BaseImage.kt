package app.base.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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
import coil.compose.AsyncImage

@Composable
fun BaseImageBig(modifier: Modifier = Modifier, image: String) {
    val imageModifier = modifier
        .size(150.dp)
        .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
        .background(Color.Gray, shape = CircleShape)
        .clip(CircleShape)

    if (image.isNotEmpty() && image.isNotBlank()) {
        AsyncImage(
            model = image,
            contentDescription = "Imagen seleccionada",
            modifier = imageModifier
        )
    } else {
        Image(
            Icons.Default.Close,
            contentDescription = "hola",
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
}

@Composable
fun BaseImageMedium(modifier: Modifier = Modifier, image: String) {
    val imageModifier = modifier
        .size(100.dp)
        .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
        .background(Color.Gray, shape = CircleShape)
        .clip(CircleShape)

    if (image.isNotEmpty() && image.isNotBlank()) {
        AsyncImage(
            model = image,
            contentDescription = "Imagen seleccionada",
            modifier = imageModifier
        )
    } else {
        Image(
            Icons.Default.Close,
            contentDescription = "hola",
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
}

@Composable
fun BaseImageSmall(modifier: Modifier = Modifier, image: String) {
    val imageModifier = modifier
        .size(50.dp)
        .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
        .background(Color.Gray, shape = CircleShape)
        .clip(CircleShape)

    if (image.isNotEmpty() && image.isNotBlank()) {
        AsyncImage(
            model = image,
            contentDescription = "Imagen seleccionada",
            modifier = imageModifier
        )
    } else {
        Image(
            Icons.Default.Close,
            contentDescription = "hola",
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
}


/*
@Composable
fun rememberSaveableBitmap(): MutableState<Bitmap?> {
    return rememberSaveable(saver = BitmapSaver()) { mutableStateOf(null) }
}*/