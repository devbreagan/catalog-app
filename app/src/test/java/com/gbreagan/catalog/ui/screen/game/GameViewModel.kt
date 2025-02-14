package com.gbreagan.catalog.ui.screen.game

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.turbine.test
import com.gbreagan.catalog.data.model.Game
import com.gbreagan.catalog.data.repository.GameRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GameViewModelTest {

    private lateinit var repository: GameRepository
    private lateinit var viewModel: GameViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        Dispatchers.setMain(testDispatcher)
        viewModel = GameViewModel(repository)
    }

    @Test
    fun `verify initial state`() = runTest {
        val initialState = viewModel.state.value
        assertEquals(true, initialState.isLoading)
        assertEquals(false, initialState.isMainDataLoaded)
        assertTrue(initialState.gameGenres.isEmpty())
        assertTrue(initialState.gamesFilteredByGenre.isEmpty())
    }

    @Test
    fun `loadGames updates state correctly`() = runTest {
        val pagingData = PagingData.from(listOf(
            Game("Game 1", "", "", "", 1, "", "", "", "", "", "")
        ))

        coEvery { repository.getPagedGameItems() } returns flowOf(pagingData)

        viewModel.loadGames()

        viewModel.state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.isMainDataLoaded)
        }
    }

    @Test
    fun `filterGames updates state with filtered games`() = runTest {
        val filteredGames = listOf(
            Game("Game 1", "", "", "Action", 1, "", "", "", "", "", "")

        )
        coEvery { repository.findItemsByGenre("Action") } returns flowOf(filteredGames)

        viewModel.filterGames("Action")

        viewModel.state.test {
            val state = awaitItem()
            assertEquals(filteredGames, state.gamesFilteredByGenre)
        }
    }

    @Test
    fun `loadGenres updates state with game genres`() = runTest {
        val genres = listOf("Action", "Adventure")
        coEvery { repository.findAllGenres() } returns flowOf(genres)

        viewModel.loadGenres()

        viewModel.state.test {
            val state = awaitItem()
            assertEquals(genres, state.gameGenres)
        }
    }

    @Test
    fun `findGames updates state with filtered games by title`() = runTest {
        val searchResults = listOf(
            Game("Game 1", "", "", "Action", 1, "", "", "", "", "", "")
        )
        coEvery { repository.findItemsByTitle("Game 1") } returns flowOf(searchResults)

        viewModel.findGames("Game 1")

        viewModel.state.test {
            val state = awaitItem()
            assertEquals(searchResults, state.gamesFilteredByGenre)
        }
    }

    @Test
    fun `test initial UiGameState is Success`() = runTest {

    }
//    @Test
//    fun `test initial UiGameState is Success`() = runTest {
//        val pagingData2 = PagingData.from(listOf(
//            Game("Game 1", "", "", "", 1, "", "", "", "", "", ""),
//            Game("Game 2", "", "", "", 2, "", "", "", "", "", ""),
//        ))
//        val fakeData = listOf(
//            Game("Game 1", "", "", "", 1, "", "", "", "", "", ""),
//            Game("Game 2", "", "", "", 2, "", "", "", "", "", ""),
//        )
//        val pagingData = Pager(PagingConfig(pageSize = 2)) {
//            FakePagingSource(fakeData)
//        }.flow
//
//
//        coEvery { repository.getPagedGameItems() } returns pagingData
//
//       launch(testDispatcher) {
//           viewModel.loadGames()
//       }
//
//        val collectedStates = mutableListOf<UiGameState>()
//        val job = launch(testDispatcher) {
//            viewModel.state.collect { collectedStates.add(it) }
//        }
//
//
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        assertTrue(viewModel.state.value is UiGameState.Success)
//        job.cancel()
//    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}



class FakePagingSource<T : Any>(private val items: List<T>) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(
            data = items,
            prevKey = null,
            nextKey = null
        )
    }
}