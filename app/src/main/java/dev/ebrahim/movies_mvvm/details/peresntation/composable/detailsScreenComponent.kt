package dev.ebrahim.movies_mvvm.details.peresntation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ebrahim.movies_mvvm.details.data.remot.model.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenComponent(
    product: Product?,
    onAddToCartClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {

            TopAppBar(title = {
                Text(text = "category : ${product?.category.toString()}")
            },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back to home screen"
                        )
                    }
                })

        },
        floatingActionButton = {
            Button(
                onClick = { onAddToCartClicked() }, modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(), colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Add to card")

            }

        }


    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            CoilImage(imageUrl = product?.image.toString())

            Text(
                text = "${product?.price.toString()} $",
                modifier = Modifier.padding(15.dp)
            )

            Text(
                text = product?.title.toString(), fontWeight = FontWeight.W700,
                style = TextStyle(color = Color.Blue),
                fontSize = 25.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = product?.description.toString())

            Row(horizontalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Count ")
                    Text(text = product?.rating?.rate.toString())
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Rate")
                    Text(text = product?.rating?.count.toString())
                }
            }
        }
    }
}