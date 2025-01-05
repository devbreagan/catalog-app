package com.gbreagan.catalog.ui.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.gbreagan.catalog.ui.component.Item
import com.gbreagan.catalog.ui.component.SimpleItemList

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    val viewModel: GameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val isAppBarVisible = remember { mutableStateOf(true) }


    LaunchedEffect(key1 = state.isLoading.not()) {
        viewModel.loadGames()
    }


    SimpleItemList(
        items = state.games.map { Item(it.id, it.title, it.thumbnail, it.shortDescription) }
    ) { itemId ->
        onItemClick(itemId)
    }
}