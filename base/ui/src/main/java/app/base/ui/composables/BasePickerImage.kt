package app.base.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/*
fun BasePickerImage() {
var imageUri by remember { mutableStateOf<Uri?>(null) }
val context = LocalContext.current

// Launcher para seleccionar la imagen
val imagePickerLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }


    // Imagen seleccionada o placeholder
    Box(
        modifier = Modifier
            .size(150.dp)
            .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
            .background(Color.Gray, shape = CircleShape)
            .clip(CircleShape)
            .clickable { imagePickerLauncher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Imagen seleccionada",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text("Seleccionar Imagen", color = Color.White)
        }
    }
}
*/