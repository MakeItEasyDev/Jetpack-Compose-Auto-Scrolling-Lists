package com.jetpack.autoscrolling

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.autoscrolling.ui.theme.RedYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY_BETWEEN_SCROLL_MS = 8L
private const val SCROLL_DX = 1f

@Composable
fun AutoScrollingList(list: List<AutoScrollingModel>) {
    var itemListState by remember { mutableStateOf(list) }
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        modifier = Modifier
    ) {
        items(itemListState.size) {
            AutoScrollingName(autoScrollingModel = itemListState[it])
            Spacer(modifier = Modifier.width(1.dp))

            if (itemListState[it] == itemListState.last()) {
                val currentList = itemListState
                val secondPart = currentList.subList(0, lazyListState.firstVisibleItemIndex)
                val firstPart = currentList.subList(lazyListState.firstVisibleItemIndex, currentList.size)

                rememberCoroutineScope().launch {
                    lazyListState.scrollToItem(0, lazyListState.firstVisibleItemScrollOffset - SCROLL_DX.toInt())
                }

                itemListState = firstPart + secondPart
            }
        }
    }

    LaunchedEffect(Unit) {
        autoScoll(lazyListState)
    }
}

private tailrec suspend fun autoScoll(lazyListState: LazyListState) {
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(SCROLL_DX)
    }
    delay(DELAY_BETWEEN_SCROLL_MS)

    autoScoll(lazyListState)
}

@Composable
fun AutoScrollingName(
    autoScrollingModel: AutoScrollingModel
) {
    Card(
        modifier = Modifier
            .size(160.dp)
            .aspectRatio(1f),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = autoScrollingModel.iconResource),
                contentDescription = autoScrollingModel.contentDescription,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(135.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = autoScrollingModel.contentDescription,
                textAlign = TextAlign.Center,
                color = RedYellow,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}






















