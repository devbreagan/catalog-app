package com.gbreagan.catalog.ui.screen.search

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.catalog.data.model.Game
import com.gbreagan.catalog.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gameRepository: GameRepository
): ViewModel() {

    fun searchGames(title: String) {
        viewModelScope.launch {
            gameRepository.findItemsByTitle(title).collectLatest {

            }
        }
    }
}
@Stable
sealed class UiGameState {
    data object Loading : UiGameState()
    data class Success(val games: List<Game>) : UiGameState()
    data class Error(val message: String) : UiGameState()
}