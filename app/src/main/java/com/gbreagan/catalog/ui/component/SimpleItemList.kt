package com.gbreagan.catalog.ui.component

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
    LazyColumn(
        modifier = modifier
    ) {
        items(count = items.itemCount) { index ->
            val item = items[index]
            item?.let {
                SimpleCard(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    title = it.title,
                    thumbnail = it.thumbnail,
                    onClick = { onItemClick(it.id) }
                )
            }
        }
    }
}

@Stable
data class Item(val id: Int, val title: String, val thumbnail: String, val description: String) {
    override fun toString(): String = title
}