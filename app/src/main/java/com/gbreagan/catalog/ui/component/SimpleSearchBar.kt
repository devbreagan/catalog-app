package com.gbreagan.catalog.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SimpleSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "",
    isActive: Boolean = false,
    onCleanLClick: () -> Unit = {}
) {
    Surface {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = placeholder, color = Color.Gray)
            },
            trailingIcon = {
                if(query.isNotEmpty())
                    IconButton(onClick = { onCleanLClick() }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    }
            },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}