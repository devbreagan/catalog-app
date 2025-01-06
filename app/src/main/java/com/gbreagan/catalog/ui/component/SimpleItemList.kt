package com.gbreagan.catalog.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.gbreagan.catalog.data.model.Game

@Composable
fun SimpleItemList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Game>,
    onItemClick: (Int) -> Unit
) {
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

        LazyColumn {

            items(count = items.itemCount) { index ->
                val item = items[index]
                item?.let {
                    SimpleCard(
                        title = it.title,
                        description = it.shortDescription,
                        thumbnail = it.thumbnail,
                        onClick = { onItemClick(it.id) }
                    )
                }
            }
        }
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            itemsIndexed(items = items, key = { _, item -> item.id }) { index, item ->
//                SimpleCard(
//                    title = item.title,
//                    description = item.description,
//                    thumbnail = item.thumbnail,
//                    onClick = { onItemClick(item.id) }
//                )
//            }
//        }
    }
}

@Stable
data class Item(val id: Int, val title: String, val thumbnail: String, val description: String) {
    override fun toString(): String = title
}