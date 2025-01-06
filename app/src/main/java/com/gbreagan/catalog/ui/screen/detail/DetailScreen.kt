package com.gbreagan.catalog.ui.screen.detail

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.gbreagan.catalog.ui.component.Item

@Composable
fun DetailScreen(
    itemId: Int
) {
    Surface {
        Text(text = "Detail $itemId")
    }
}