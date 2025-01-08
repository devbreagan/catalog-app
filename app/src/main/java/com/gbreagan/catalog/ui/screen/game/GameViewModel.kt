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
    private val _uiState: MutableStateFlow<UiGameState> =
        MutableStateFlow(
            UiGameState(isLoading = true)
        )
    val state: StateFlow<UiGameState> = _uiState.asStateFlow()

    val games: Flow<PagingData<Game>> = gameRepository
        .getPagedGameItems()
        .cachedIn(viewModelScope)

    fun loadGames() {
        viewModelScope.launch {
            games.collectLatest { pagingData ->
                _uiState.update {
                    if (pagingData != PagingData.empty<Game>()) {
                       it.copy(isLoading = false, isMainDataLoaded = true)
                    } else {
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }

    fun filterGames(genre: String) {
        viewModelScope.launch {
            gameRepository.findItemsByGenre(genre)
                .collectLatest { genres ->
                    _uiState.update {
                        it.copy(gamesFilteredByGenre = genres)
                    }
                }
        }
    }
    fun loadGenres() {
        viewModelScope.launch {
            gameRepository.findAllGenres()
                .collectLatest { genres ->
                    _uiState.update {
                       it.copy(gameGenres = genres)
                    }
                }
        }
    }

    fun findGames(title: String) {
        viewModelScope.launch {
            gameRepository.findItemsByTitle(title)
                .collectLatest { genres ->
                    _uiState.update {
                        it.copy(gamesFilteredByGenre = genres)
                    }
                }
        }
    }
}

@Stable
data class UiGameState(
    val isLoading: Boolean = false,
    val isMainDataLoaded: Boolean = false,
    val gameGenres: List<String> = listOf(),
    val gamesFilteredByGenre: List<Game> = listOf()
)