package com.gbreagan.catalog.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                SimpleCard(
                    title = item.title,
                    description = item.description,
                    thumbnail = item.thumbnail,
                    onClick = { onItemClick(item.id) }
                )
            }
        }
    }
}

@Stable
data class Item(val id: Int, val title: String, val thumbnail: String, val description: String) {
    override fun toString(): String = title
}