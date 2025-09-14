package com.furkan.mortymap.presentation.origins

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.furkan.mortymap.data.model.Origin
import com.furkan.mortymap.presentation.base.PagingList

@Composable
fun OriginsScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel = hiltViewModel(),
    contentPadding: PaddingValues = PaddingValues()
) {
    val locations: LazyPagingItems<Origin> = viewModel.locations.collectAsLazyPagingItems()

    Column(modifier = modifier.padding(contentPadding)) {
        PagingList(items = locations, modifier = modifier) { location ->
            LocationItem(location)
        }
    }
}