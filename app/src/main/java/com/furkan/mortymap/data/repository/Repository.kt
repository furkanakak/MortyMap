package com.furkan.mortymap.data.repository

import androidx.paging.PagingData
import com.furkan.mortymap.data.model.Character
import com.furkan.mortymap.data.model.Origin
import com.furkan.mortymap.data.remote.ApiService
import com.furkan.mortymap.di.NetworkLatency
import com.furkan.mortymap.domain.BasePagerFactory
import com.furkan.mortymap.domain.repository.BaseRepository
import com.furkan.mortymap.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService
) : BaseRepository(), IRepository {
    override fun fetchPagedCharacters(): Flow<PagingData<Character>> =
        BasePagerFactory.create { page ->
            NetworkLatency.delayForPage(page)
            val list = service.fetchCharacters(page).body()?.results.orEmpty()
            retrofit2.Response.success(list)
        }.flow

    override fun fetchPagedLocation(): Flow<PagingData<Origin>> =
        BasePagerFactory.create { page ->
            NetworkLatency.delayForPage(page)
            val list = service.fetchLocation(page).body()?.results.orEmpty()
            retrofit2.Response.success(list)
        }.flow
}