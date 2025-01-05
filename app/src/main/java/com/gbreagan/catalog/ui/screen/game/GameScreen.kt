package com.gbreagan.catalog.ui.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gbreagan.catalog.ui.component.Item
import com.gbreagan.catalog.ui.component.SimpleItemList

@Composable
fun GameScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: GameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.isLoading.not()) {
        viewModel.loadGames()
    }
    SimpleItemList(items = state.games.map { Item(it.id, it.title, it.thumbnail) }, modifier = modifier)
}