package com.furkan.mortymap.domain.repository

import androidx.paging.PagingData
import com.furkan.mortymap.data.model.Character
import com.furkan.mortymap.data.model.Origin
import com.furkan.mortymap.data.model.OriginResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun fetchPagedCharacters(): Flow<PagingData<Character>>
    fun fetchPagedLocation(): Flow<PagingData<Origin>>
}