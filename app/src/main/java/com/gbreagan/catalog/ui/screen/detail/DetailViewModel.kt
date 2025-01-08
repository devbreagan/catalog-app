package com.gbreagan.catalog.ui.screen.detail

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
class DetailViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel(
) {
    private val _uiState = MutableStateFlow<UiDetailState>(UiDetailState.Loading)
    val state: StateFlow<UiDetailState> = _uiState.asStateFlow()

    fun loadGame(id: Int) {
        viewModelScope.launch {
            gameRepository.getGameItem(id).collectLatest { game ->
                _uiState.update {
                    UiDetailState.Success(game)
                }
            }
        }
    }
}

@Stable
sealed class UiDetailState {
    data object Loading : UiDetailState()
    data class Success(val game: Game) : UiDetailState()
    data class Error(val message: String) : UiDetailState()
}