package com.gbreagan.catalog.ui.screen.game

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.catalog.data.model.Game
import com.gbreagan.catalog.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel(
) {
    private val _uiState = MutableStateFlow(UiHomeState())
    val state: StateFlow<UiHomeState> = _uiState.asStateFlow()

    fun loadGames() {
        Log.d("GameViewModel", "loadGames")
        viewModelScope.launch {
//            _uiState.update { it.copy(isLoading = true) }
            gameRepository.games().collect { value ->
                _uiState.update {
                    it.copy(games = value, isLoading = false)
                }
            }
        }
    }
}

@Stable
data class UiHomeState(
    val isLoading: Boolean = true,
    val games: List<Game> = ArrayList()
)