package dev.ebrahim.movies_mvvm.details.peresntation.composable

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CoilImage(imageUrl : String) {
   val painter = rememberAsyncImagePainter(model = imageUrl)
   Image(painter = painter, contentDescription = "image" ,
      modifier = Modifier.padding(20.dp).fillMaxWidth()
         .height(400.dp)
         )
}