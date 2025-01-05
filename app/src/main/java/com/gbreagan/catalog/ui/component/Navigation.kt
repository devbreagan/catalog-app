package com.gbreagan.catalog.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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



@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}

@Serializable
sealed interface Screen {
    @Serializable data object Game : Screen
    @Serializable data class Detail(val itemId: Int) : Screen
}