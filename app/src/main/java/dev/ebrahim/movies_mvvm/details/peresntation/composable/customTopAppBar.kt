package dev.ebrahim.movies_mvvm.details.peresntation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    onIconClicked: () -> Unit,
    icon: ImageVector = Icons.Filled.MoreVert,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onIconClicked() },

                ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "back to home screen",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Blue)
    )
}