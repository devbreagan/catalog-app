package com.gbreagan.catalog.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SimpleCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    thumbnail: String,
    onClick:() -> Unit
) {

    val showShimmer = remember { mutableStateOf(true) }

    Card(
        modifier = modifier

            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp)
    ){
        AsyncImage(
            modifier = Modifier
                //.background(SimpleShimmer(targetValue = 1300f, showShimmer = showShimmer.value))
                .heightIn(min = 100.dp)
                .fillMaxWidth(),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(thumbnail)
                .crossfade(true)
                .build(),
            //onSuccess = { showShimmer.value = false },
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}