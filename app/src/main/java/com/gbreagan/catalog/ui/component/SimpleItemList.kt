package com.gbreagan.catalog.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gbreagan.catalog.R

@Composable
fun SimpleItemList(
    modifier: Modifier = Modifier,
    items: List<Item>,
    onItemClick: (Int) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    val filteredItems = remember(searchText) {
        if (searchText.isBlank()) items
        else items.filter { it.title.contains(searchText, ignoreCase = true) }
    }

    Column(modifier = modifier.padding(16.dp)) {
//        TextField(
//            enabled = false,
//            value = searchText,
//            onValueChange = { searchText = it },
//            label = { Text(stringResource(id = R.string.label_search)) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            singleLine = true
//        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = items, key = { _, item -> item.id }) { index, item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .clickable {
                            onItemClick(item.id)
                        },
                    shape = RoundedCornerShape(8.dp)
                ){
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(item.thumbnail)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                    )
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = item.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Stable
data class Item(val id: Int, val title: String, val thumbnail: String, val description: String) {
    override fun toString(): String = title
}