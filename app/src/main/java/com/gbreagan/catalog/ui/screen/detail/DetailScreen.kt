package com.gbreagan.catalog.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gbreagan.catalog.data.model.Game
import com.gbreagan.catalog.ui.component.Item
import com.gbreagan.catalog.ui.component.SimpleItemGrid
import com.gbreagan.catalog.ui.component.SimpleLoading
import com.gbreagan.catalog.ui.screen.game.GameViewModel
import com.gbreagan.catalog.ui.screen.game.UiGameState

@Composable
fun DetailScreen(
    itemId: Int,
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state is UiDetailState.Loading) {
        viewModel.loadGame(itemId)
    }

    when (state) {
        is UiDetailState.Loading -> {
            SimpleLoading()
        }
        is UiDetailState.Error -> {
            Text(text = "Error!!!")
        }
        is UiDetailState.Success -> {
            DetailScreenLayout((state as UiDetailState.Success).game)
        }
    }

}
@Composable
private fun DetailScreenLayout(
    game: Game,
) {
    Column {
        AsyncImage(
            modifier = Modifier
                //.background(SimpleShimmer(targetValue = 1300f, showShimmer = showShimmer.value))
                .heightIn(min = 200.dp)
                .fillMaxWidth(),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(game.thumbnail)
                .crossfade(true)
                .build(),
            //onSuccess = { showShimmer.value = false },
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = game.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = game.shortDescription,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = game.genre,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = game.platform,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = game.publisher,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}