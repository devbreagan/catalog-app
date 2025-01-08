package com.gbreagan.catalog.ui.screen.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(

) {

    Column {
//        SearchBar(
//            query = "title.value",
//            onQueryChange = {
//                Log.i("GBMS", "onQueryChange")
//            },
//            onSearch = {
//                Log.i("GBMS", "onSearch")
//            },
//            active = true,
//            onActiveChange = {
//                Log.i("GBMS", "onActiveChange")
//            }
//        ) {
//
//        }
        Text(text = "Search: ")
    }

}