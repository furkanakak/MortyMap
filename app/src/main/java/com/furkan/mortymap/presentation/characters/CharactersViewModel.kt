package com.furkan.mortymap.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.furkan.mortymap.data.model.Character
import com.furkan.mortymap.data.repository.Repository
import javax.inject.Inject
import com.furkan.mortymap.domain.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class CharactersViewModel @Inject constructor(repository: Repository) : ViewModel() {
    val characters: Flow<PagingData<Character>> =
        repository.fetchPagedCharacters().cachedIn(viewModelScope)
}