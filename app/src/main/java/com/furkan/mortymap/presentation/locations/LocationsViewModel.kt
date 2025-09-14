package com.furkan.mortymap.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.furkan.mortymap.data.model.Origin
import com.furkan.mortymap.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(repository: Repository) : ViewModel() {
    val locations: Flow<PagingData<Origin>> =
        repository.fetchPagedLocation().cachedIn(viewModelScope)
}