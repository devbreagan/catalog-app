package com.gbreagan.catalog.ui.screen.game

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.gbreagan.catalog.R
import com.gbreagan.catalog.ui.component.SimpleChipCarousel
import com.gbreagan.catalog.ui.component.SimpleItemGrid
import com.gbreagan.catalog.ui.component.SimpleSearchBar

@Composable
fun GameScreen(
    onItemClick: (Int) -> Unit
) {
    val viewModel: GameViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val currentGenre = remember { mutableStateOf<String?>(null) }
    val isFiltering = remember { mutableStateOf(false) }
    val isSearching = remember { mutableStateOf(false) }
    val query = remember { mutableStateOf("") }

    LaunchedEffect(state.isLoading) {
        viewModel.loadGames()
        viewModel.loadGenres()
    }

    Column {
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (isSearching.value.not())
                    SimpleChipCarousel(
                        genres = state.gameGenres,
                        selectedName = currentGenre.value
                    ) {
                        isFiltering.value = true
                        currentGenre.value = it
                        if (it != null)
                            viewModel.filterGames(it)
                    }
                else {
                    SimpleSearchBar(
                        query = query.value,
                        onQueryChange = {
                            query.value = it
                            viewModel.findGames(it)
                        },
                        placeholder = stringResource(id = R.string.label_search),
                        onCleanLClick = {
                            query.value = ""
                        }
                    )
                }
            }
            Column(
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                IconButton(onClick = {
                    if (isSearching.value.not()) { // lupa
                        currentGenre.value = null
                        isFiltering.value = false
                    } else { // x
                    }
                    query.value = ""
                    isSearching.value = !isSearching.value
                }) {
                    Icon(
                        imageVector = if (isSearching.value.not()) Icons.Filled.Search else Icons.Filled.Close,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                }
            }
        }
        if (isFiltering.value || isSearching.value)
            SimpleItemGrid(
                items = state.gamesFilteredByGenre,
            ) { itemId ->
                onItemClick(itemId)
            }
        else
            SimpleItemGrid(
                items = viewModel.games.collectAsLazyPagingItems(),
            ) { itemId ->
                onItemClick(itemId)
            }
    }
}