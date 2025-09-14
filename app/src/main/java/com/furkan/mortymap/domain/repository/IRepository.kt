package com.furkan.mortymap.domain.repository

import androidx.paging.PagingData
import com.furkan.mortymap.data.model.Character
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun fetchPagedCharacters(): Flow<PagingData<Character>>
}