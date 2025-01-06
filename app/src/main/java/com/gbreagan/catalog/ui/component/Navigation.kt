package com.gbreagan.catalog.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gbreagan.catalog.ui.screen.detail.DetailScreen
import com.gbreagan.catalog.ui.screen.game.GameScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screen.Game) {

        composable<Screen.Game> {
            GameScreen {
                navController.navigate(Screen.Detail(it))
            }
        }

        composable<Screen.Detail> { backStackEntry ->
            val args : Screen.Detail = backStackEntry.toRoute()
            DetailScreen(itemId = args.itemId)
        }
    }
}

@Serializable
sealed interface Screen {
    @Serializable data object Game : Screen
    @Serializable data class Detail(val itemId: Int) : Screen
}