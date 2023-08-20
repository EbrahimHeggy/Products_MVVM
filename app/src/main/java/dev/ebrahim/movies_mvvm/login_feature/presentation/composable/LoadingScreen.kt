package dev.ebrahim.movies_mvvm.login_feature.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.ebrahim.movies_mvvm.R

@Composable
fun LoadingScreen(
    isLoading: Boolean
) {
    if (!isLoading) return
    Dialog(onDismissRequest = { /*TODO*/ }) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(8.dp)
                    .height(150.dp)
                    .width(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = stringResource(R.string.loading), fontSize = 20.sp, color = Color.Black)
            }

    }


}