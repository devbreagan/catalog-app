package com.gbreagan.catalog.ui.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.gbreagan.catalog.data.model.Game

@Composable
fun SimpleItemGrid(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Game>,
    onItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(items) { item ->
            item?.let {
                SimpleCard(
                    title = it.title,
                    thumbnail = it.thumbnail,
                    onClick = { onItemClick(it.id) }
                )
            }
        }
    }
}

@Composable
fun SimpleItemGrid(
    modifier: Modifier = Modifier,
    items: List<Game>,
    onItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        itemsIndexed(items) { _, item ->
            SimpleCard(
                title = item.title,
                thumbnail = item.thumbnail,
                onClick = { onItemClick(item.id) }
            )
        }
    }
}

fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}