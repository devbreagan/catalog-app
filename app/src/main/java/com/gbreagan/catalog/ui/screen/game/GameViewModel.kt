package com.gbreagan.catalog.ui.screen.game

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gbreagan.catalog.data.model.Game
import com.gbreagan.catalog.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel(
) {
    private val _uiState = MutableStateFlow<UiGameState>(UiGameState.Loading)
    val state: StateFlow<UiGameState> = _uiState.asStateFlow()

    val games: Flow<PagingData<Game>> = gameRepository
        .getPagedGameItems()
        .cachedIn(viewModelScope)

    fun loadGames() {
        viewModelScope.launch {
            games.collectLatest { pagingData ->
                _uiState.update {
                    if (pagingData != PagingData.empty<Game>()) {
                        UiGameState.Success
                    } else {
                        UiGameState.Error("No items found")
                    }
                }
            }
        }
    }
}

@Stable
sealed class UiGameState {
    data object Loading : UiGameState()
    data object Success : UiGameState()
    data class Error(val message: String) : UiGameState()
}