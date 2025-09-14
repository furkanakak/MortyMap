package com.furkan.mortymap.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.furkan.mortymap.common.anotation.SelectableId
import com.furkan.mortymap.common.getField

@Composable
fun <T : Any> PagingList(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    itemContent: @Composable LazyItemScope.(T) -> Unit
) {
    val keySelector: (T) -> Any = { t -> t.getField<SelectableId>() ?: t.hashCode() }

    val refresh = items.loadState.refresh
    val showInitialOverlay = items.itemCount == 0 &&
            (refresh is LoadState.Loading || refresh is LoadState.Error)
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        if (showInitialOverlay) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp) // dış padding Box’ta
            ) {
                items(items = items, key = { keySelector(it) }) { value ->
                    value?.let { itemContent(it) }
                }

                if (items.loadState.append is LoadState.Loading) {
                    item("append_loader") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) { CircularProgressIndicator() }
                    }
                }
            }
        }
    }
}