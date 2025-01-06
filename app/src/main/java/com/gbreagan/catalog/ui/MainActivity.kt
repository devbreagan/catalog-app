package com.gbreagan.catalog.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.gbreagan.catalog.ui.component.Navigation
import com.gbreagan.catalog.ui.screen.main.MainScreen
import com.gbreagan.catalog.ui.theme.CatalogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CatalogTheme {
                Navigation(rememberNavController())
                MainScreen()
            }
        }
    }
}