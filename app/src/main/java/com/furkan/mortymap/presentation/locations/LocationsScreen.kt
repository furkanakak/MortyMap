package com.furkan.mortymap.presentation.locations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.furkan.mortymap.data.model.Origin
import com.furkan.mortymap.presentation.base.PagingList

@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel = hiltViewModel()
) {
    val locations: LazyPagingItems<Origin> = viewModel.locations.collectAsLazyPagingItems()

    PagingList(items = locations, modifier = modifier) { location ->
        LocationItem(location)
    }
}