package com.furkan.mortymap.presentation.base

import androidx.compose.foundation.layout.*
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
    itemContent: @Composable LazyItemScope.(T) -> Unit
) {
    val keySelector: (T) -> Any = { t -> t.getField<SelectableId>() ?: t.hashCode() }

    LazyColumn(modifier = modifier.fillMaxSize()) {
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