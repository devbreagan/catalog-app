package com.gbreagan.catalog.ui.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.gbreagan.catalog.ui.component.SimpleItemList
import com.gbreagan.catalog.ui.component.SimpleLoading

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    val viewModel: GameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        is UiGameState.Loading -> {
            SimpleLoading(isDisplayed = true)
        }
        is UiGameState.Error -> TODO()
        is UiGameState.Success -> {
            SimpleItemList(
                items = viewModel.games.collectAsLazyPagingItems(),
            ) { itemId ->
                onItemClick(itemId)
            }
        }
    }
}