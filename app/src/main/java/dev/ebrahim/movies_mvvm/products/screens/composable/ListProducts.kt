package dev.ebrahim.movies_mvvm.products.screens.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import dev.ebrahim.movies_mvvm.products.data.remote.model.news.model.ProductItem

@Composable
fun ListProducts(list: List<ProductItem>, navController: NavController) {
    if (list.isNullOrEmpty()) return
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list) { it
            ElevatedCard(
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp)
                    .clickable {
                        // TODO For Navigation

                    }
                ){
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(it.image),
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxHeight(0.6f)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Title: ${it.title}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Description : ${it.description} ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
