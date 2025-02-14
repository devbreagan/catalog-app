package com.gbreagan.catalog.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gbreagan.catalog.ui.theme.Pink40
import com.gbreagan.catalog.ui.theme.Purple40
import com.gbreagan.catalog.ui.theme.Purple80
import com.gbreagan.catalog.ui.theme.PurpleGrey40

@Composable
fun SimpleChipCarousel(
    genres: List<String>,
    selectedName: String?,
    onClick: (String?) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(genres) { _, item ->
            SimpleChip(
                selected = item == selectedName,
                genre = item,
                onclick = { onClick(item) }
            )
        }
    }
}

@Composable
fun SimpleChip(
    selected: Boolean,
    genre: String,
    onclick: () -> Unit,
) {
    val animateChipBackgroundColor by animateColorAsState(
        targetValue = if (selected) Purple80 else Purple40,
        animationSpec = tween(durationMillis = 50, easing = LinearOutSlowInEasing), label = "ColorAnimation"
    )

    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .background(animateChipBackgroundColor)
            .padding(horizontal = 8.dp)
            .height(40.dp)
            .widthIn(min = 80.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onclick
            )
    ) {
        Text(
            text = genre,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            color = Color.White.copy(alpha = 0.80F),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}