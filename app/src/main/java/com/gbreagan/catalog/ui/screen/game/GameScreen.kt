package com.gbreagan.catalog.ui.screen.game

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.gbreagan.catalog.ui.component.SimpleItemGrid
import com.gbreagan.catalog.ui.component.SimpleItemList
import com.gbreagan.catalog.ui.component.SimpleLoading

@Composable
fun GameScreen(
    onItemClick: (Int) -> Unit
) {
    val viewModel: GameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
        is UiGameState.Loading -> {
            SimpleLoading()
        }
        is UiGameState.Error -> {
            Text(text = "Error!!!")
        }
        is UiGameState.Success -> {
            SimpleItemGrid(
                items = viewModel.games.collectAsLazyPagingItems(),
            ) { itemId ->
                onItemClick(itemId)
            }
        }
    }
}