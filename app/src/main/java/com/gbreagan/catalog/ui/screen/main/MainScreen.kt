package com.gbreagan.catalog.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.gbreagan.catalog.ui.component.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Games")
                }
            )
//            SearchBar("", {}, {}, active = false, onActiveChange = {}) {
//
//            }
        }
    ) { innerPadding ->
        Column(
          modifier = Modifier.padding(innerPadding)
        ) {
            Navigation(navController)
        }
    }
}